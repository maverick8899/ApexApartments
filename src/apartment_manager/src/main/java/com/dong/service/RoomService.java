package com.dong.service;

import com.dong.pojo.Room;

import java.util.List;

public interface RoomService {
    List<Room> getRoom();
    Room getRoomById(int id);
    boolean addOrUpdateRoom(Room r );
    boolean deleteRoom(int id);


}
