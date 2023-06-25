package com.thegreatapi.opensocialanalytics;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.dashbuilder.dataset.ColumnType;
import org.dashbuilder.dataset.DataSet;
import org.dashbuilder.displayer.DisplayerSettings;
import org.dashbuilder.dsl.factory.component.ComponentFactory;
import org.dashbuilder.dsl.factory.dashboard.DashboardFactory;
import org.dashbuilder.dsl.model.Dashboard;
import org.dashbuilder.dsl.model.Navigation;
import org.dashbuilder.dsl.model.Page;
import org.dashbuilder.dsl.serialization.DashboardExporter;

import java.util.List;

import static org.dashbuilder.dataset.DataSetFactory.newDataSetBuilder;
import static org.dashbuilder.displayer.DisplayerSettingsFactory.newBarChartSettings;
import static org.dashbuilder.dsl.factory.navigation.NavigationFactory.group;
import static org.dashbuilder.dsl.factory.navigation.NavigationFactory.item;
import static org.dashbuilder.dsl.factory.navigation.NavigationFactory.navigation;
import static org.dashbuilder.dsl.factory.page.PageFactory.page;
import static org.dashbuilder.dsl.factory.page.PageFactory.row;

@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        DataSet dataSet = newDataSetBuilder().column("Country", ColumnType.LABEL)
                .column("Population", ColumnType.NUMBER)
                .row("Brazil", "211")
                .row("United States", "328")
                .row("Cuba", "11")
                .row("India", "1366")
                .row("China", "1398")
                .buildDataSet();
        DisplayerSettings populationBar = newBarChartSettings().subType_Column()
                .width(800)
                .height(600)
                .dataset(dataSet)
                .column("Country")
                .column("Population")
                .buildSettings();
        Page page = page("Countries Population",
                row("<h3> Countries Population </h3>"),
                row(ComponentFactory.displayer(populationBar)));
        Navigation navigation = navigation(group("Countries Information", item(page)));
        Dashboard populationDashboard = DashboardFactory.dashboard(List.of(page), navigation);

        DashboardExporter.get().export(populationDashboard,
                "/path/to/export.zip",
                DashboardExporter.ExportType.ZIP);


        return "Hello from RESTEasy Reactive";
    }
}
