package com.dong.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author MAVERICK
 */
@Controller
public class UserController {

    @GetMapping({"/login"})
    public String login() {
        return "login";
    }
}
