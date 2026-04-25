package com.backend.resource;

import com.backend.dao.*;
import com.backend.model.SensorReading;
import com.backend.model.Sensor;
import java.util.ArrayList;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


public class SensorReadingResource {

    private String sensorId;

    public SensorReadingResource(String sensorId) {
        this.sensorId = sensorId;
    }

    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    private GenericDAO<SensorReading> sensorReadingDAO = new GenericDAO<>(MockDatabase.SREADING);
    private GenericDAO<Sensor> sensorDAO = new GenericDAO<>(MockDatabase.SENSOR);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<SensorReading> getSensorReaadings() {
        List<SensorReading> allSensorReadings = sensorReadingDAO.getAll();

        List<SensorReading> filteredSensorReading = new ArrayList<>();

        for (SensorReading reading : allSensorReadings) {
            if (reading.getSensorId().equals(sensorId)) {
                filteredSensorReading.add(reading);
            }
        }

        return filteredSensorReading;
    }
    
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public SensorReading addNewReading(SensorReading newReading){
        newReading.setSensorId(sensorId);

        SensorReading newAddedReading = sensorReadingDAO.add(newReading);

        Sensor sensor = sensorDAO.getById(sensorId);
            
        sensor.setCurrentValue(newReading.getValue());
            
        sensorDAO.update(sensor);
            
        return newAddedReading;
    }
    

}


