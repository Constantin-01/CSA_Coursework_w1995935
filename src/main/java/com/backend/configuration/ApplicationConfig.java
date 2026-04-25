package com.backend.configuration;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

//This class sets the base endpoint.
//Activates JAX-RS and scans the available resources.

@ApplicationPath("/api/v1") //All REST endpoints must start with /api/v1
public class ApplicationConfig extends Application{
    
}
