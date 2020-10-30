package com.zoo.controller;

import com.zoo.service.ReportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private ReportsService reportsService;


    @GetMapping("/animalswithoutroom")
    private ResponseEntity getAnimalsWithoutRooms(@RequestParam String column, @RequestParam String order) {
        return reportsService.getAnimalsWithoutRooms(column, order);
    }

    @GetMapping("/animalsinroom")
    private ResponseEntity getAnimalsInRoom(@RequestParam String room, @RequestParam String column, @RequestParam String order) {
        return reportsService.getAnimalsInRoom(room, column, order);
    }

    @GetMapping("/favroom")
    private ResponseEntity getFavRoomsList(@RequestParam String id) {
        return reportsService.getFavRoomsList(id);
    }
}
