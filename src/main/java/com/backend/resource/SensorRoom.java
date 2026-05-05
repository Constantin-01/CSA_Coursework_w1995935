package com.backend.resource;

import com.backend.dao.*;
import com.backend.model.Room;
import com.backend.model.Sensor;
import com.backend.exception.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/rooms")
public class SensorRoom {
    
    private GenericDAO<Sensor> sensorDAO = new GenericDAO<>(MockDatabase.SENSOR);
    private GenericDAO<Room> sensorRoomDAO = new GenericDAO<>(MockDatabase.ROOM);
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Room> getAllRooms(){
        return sensorRoomDAO.getAll();
    }
    
    @GET
    @Path("/{roomId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Room getRoomById(@PathParam("roomId") String roomId){
        return sensorRoomDAO.getById(roomId);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addRoom(Room room){
        sensorRoomDAO.add(room);
    }
    
    @DELETE
    @Path("/{roomId}")
    public void deleteRoom(@PathParam("roomId") String roomId) {
        
        boolean hasSensor = false;
       
        for (Sensor sensor : sensorDAO.getAll()) {
            if(roomId.equals(sensor.getRoomId())){
                hasSensor = true;
                break;
            }
        }
        
        if (hasSensor){
            throw new RoomNotEmptyException("This room cannot be deleted as it is currently occupied by active sensors");
        }
        
        sensorRoomDAO.delete(roomId);
    }
    
}
