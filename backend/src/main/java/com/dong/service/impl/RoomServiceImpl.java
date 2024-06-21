package com.dong.service.impl;

import com.dong.pojo.Room;
import com.dong.repository.RoomRepository;
import com.dong.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service

public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomReps;
    @Override
    public List<Room> getRoom(Map<String, String> params) {
        return this.roomReps.getRoom(params );
    }

    @Override
    public Room getRoomById(int id) {
        return this.roomReps.getRoomById(id);
    }

    @Override
    public boolean addOrUpdateRoom(Room r) {
        return this.roomReps.addOrUpdateRoom(r);
    }

    @Override
    public boolean deleteRoom(int id) {
        return this.roomReps.deleteRoom(id);
    }
}
