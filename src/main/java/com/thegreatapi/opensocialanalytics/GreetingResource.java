package com.thegreatapi.opensocialanalytics;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<List<String>> hello() {
        return List.of(
                List.of("\"John\", \"Doe\""),
                List.of("\"James\", \"Hetfield\""),
                List.of("\"Robert\", \"Trujillo\"")
        );
    }
}
