package com.dong.controllers;
import com.dong.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")

public class ApiNotificationController {

    private final NotificationService notificationService;

    @Autowired
    public ApiNotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/notify")
    public void handleEvent(@RequestBody String event) {

        notificationService.sendNotification(event);
    }
}
