package com.dong.service;

import com.dong.pojo.Room;

import java.util.List;
import java.util.Map;

public interface RoomService {
    List<Room> getRoom(Map<String, String> params);
    Room getRoomById(int id);
    boolean addOrUpdateRoom(Room r );
    boolean deleteRoom(int id);


}
