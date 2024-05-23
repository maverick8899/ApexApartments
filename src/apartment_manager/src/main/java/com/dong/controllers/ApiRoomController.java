//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.dong.controllers;

import com.dong.pojo.Room;
import com.dong.service.RoomService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api"})
public class ApiRoomController {
    @Autowired
    private RoomService roomSer;

    public ApiRoomController() {
    }

    @DeleteMapping({"/room/{id}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        this.roomSer.deleteRoom(id);
    }

    @RequestMapping({"/room/"})
    @CrossOrigin
    public ResponseEntity<List<Room>> list() {
        return new ResponseEntity(this.roomSer.getRoom(), HttpStatus.OK);
    }
}
