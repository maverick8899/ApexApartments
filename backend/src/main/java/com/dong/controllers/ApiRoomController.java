package com.dong.controllers;

import com.dong.pojo.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.dong.service.RoomService;


import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiRoomController {
    @Autowired
    private RoomService roomSer;
    @DeleteMapping("/room/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "id") int id) {
        this.roomSer.deleteRoom(id);
    }
    @RequestMapping("/room/")
    @CrossOrigin
    public ResponseEntity<List<Room>> list() {
        return new ResponseEntity<>(this.roomSer.getRoom(), HttpStatus.OK);
    }
}
