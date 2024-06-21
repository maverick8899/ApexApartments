package com.dong.controllers;
import com.dong.pojo.Room;
import com.dong.service.RoomService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@ControllerAdvice
@PropertySource("classpath:configs.properties")
public class RoomController {

    @Autowired
    private RoomService roomSer;


    @RequestMapping("/room")
    public String list(Model model,@RequestParam Map<String, String> params) {
        model.addAttribute("room", this.roomSer.getRoom(params));
        return "room";
    }

    @GetMapping("/addroom")
    public String list1(Model model) {
        model.addAttribute("room", new Room());
        return "addroom";
    }
    @GetMapping("/addroom/{id}")
    public String update(Model model, @PathVariable(value = "id") int id)  {
        model.addAttribute("room", this.roomSer.getRoomById(id));
        return "addroom";
    }
    @PostMapping("/addroom")
    public String add(@ModelAttribute(value = "room") @Valid Room c,
                      BindingResult rs) {
        if (!rs.hasErrors())
            if (roomSer.addOrUpdateRoom(c) == true)
                return "redirect:/room";

        return "addroom";
    }

}
