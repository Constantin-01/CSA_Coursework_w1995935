package com.backend.resource;

import com.backend.dao.*;
import com.backend.model.Sensor;
import com.backend.model.Room;
import com.backend.model.SensorReading;
import java.util.ArrayList;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/sensors")
public class SensorResource {

    private GenericDAO<Sensor> sensorDAO = new GenericDAO<>(MockDatabase.SENSOR);
    private GenericDAO<Room> sensorRoomDAO = new GenericDAO<>(MockDatabase.ROOM);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Sensor> getAllSensors(@QueryParam("type") String type) {
        if (type == null) {
            return sensorDAO.getAll();
        }
        
        List<Sensor> filteredSensors = new ArrayList<>();
        
        for (Sensor sensor : sensorDAO.getAll()){
            if (sensor.getType().equals(type)){
                filteredSensors.add(sensor);
            }
        }
        
        return filteredSensors;
    }

    @GET
    @Path("/{sensorId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Sensor getSensorById(@PathParam("sensorId") String sensorId) {
        return sensorDAO.getById(sensorId);
    }
    
    @Path("/{sensorId}/readings")
    public SensorReadingResource getSensorReadingResource(@PathParam("sensorId") String Id){
        return new SensorReadingResource(Id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addSensor(Sensor sensor) {
        if (sensorRoomDAO.getById(sensor.getRoomId()) == null) {
            throw new WebApplicationException("The room ID you have provided does not exist!", 400);
        }

        sensorDAO.add(sensor);
    }
}
