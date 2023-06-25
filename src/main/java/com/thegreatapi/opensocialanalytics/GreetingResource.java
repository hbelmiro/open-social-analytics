package com.thegreatapi.opensocialanalytics;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String hello() {
        return """
                {
                    "metric": "my_metric",
                    "label": "My awesome metric",
                    "value": 42
                }
                """;
    }


}
