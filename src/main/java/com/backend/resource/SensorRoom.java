package com.backend.resource;

import com.backend.dao.*;
import com.backend.model.Room;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/rooms")
public class SensorRoom {
    
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
        sensorRoomDAO.delete(roomId);
    }
    
}
