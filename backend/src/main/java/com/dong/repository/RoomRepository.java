package com.dong.repository;

import com.dong.pojo.Customer;
import com.dong.pojo.Room;

import java.util.List;

public interface RoomRepository {
    List<Room> getRoom();
    Room getRoomById(int id);
    boolean addOrUpdateRoom(Room r );
    boolean deleteRoom(int id);

}
