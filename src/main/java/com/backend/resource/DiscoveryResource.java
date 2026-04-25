package com.backend.resource;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.*;

//Discovery class crates an endpoint for the metadata in formation.
//It helps the client find the resources hyperlinks without having to 
//rely on documentation.

@Path("/")
public class DiscoveryResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> apiInfo() {
        
        //linked hash map to keep track of insertion order.
        //This gives control over the order in which information is displayed 
        //on the client side
        //Also it is a nested data structure. Map within a Map.
        Map<String, Object> metadata = new LinkedHashMap<>();

        metadata.put("version", "v1");
        metadata.put("contact", "information@info.com");

        Map<String, String> resourceInfo = new LinkedHashMap<>();
        resourceInfo.put("rooms", "/api/v1/rooms");
        resourceInfo.put("sensors", "/api/v1/sensors");
        resourceInfo.put("sensorReadings", "/api/v1/sensors/{sensorId}/sensorReadings");

        metadata.put("resources", resourceInfo);

        return metadata;
    }
}
