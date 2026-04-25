package com.backend.dao;

import com.backend.model.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//This class acts as the database layer containting all the class objects.

public class MockDatabase {
    public final static List<Room> ROOM = new ArrayList<>();
    public final static List<Sensor> SENSOR = new ArrayList<>();
    public final static List<SensorReading> SREADING = new ArrayList<>();
    
    static{
        ROOM.add(new Room("R-1", "Lecture Hall", 200 ));
        ROOM.add(new Room("R-2", "Seminar Class", 30 ));
        
        SENSOR.add(new Sensor("T-1", "Temperature", "Active", 19, ROOM.get(0).getId()));
        SENSOR.add(new Sensor("T-2", "CO2", "Active", 17.5, ROOM.get(1).getId()));
        
        SREADING.add(new SensorReading("SR-01", 174523794L, 2.2, "T-1" ));
        SREADING.add(new SensorReading("SR-02", 189793794L, 3.4, "T-2" ));
    }
}
