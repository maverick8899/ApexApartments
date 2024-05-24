package com.dong.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
@RestController

public class ApiNotificationController {
    @Autowired
    private NotificationController notificationController;

    @RequestMapping(value = "/push", method = RequestMethod.GET)
    public String pushNotification() {
        notificationController.notifyFrontend("This is a test notification!");
        return "Notification sent!";
    }
}
