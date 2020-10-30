package com.zoo.controller;

import com.zoo.model.RoomsModel;
import com.zoo.service.RoomsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rooms")
public class RoomsController {

    @Autowired
    private RoomsService roomsService;

    @PostMapping("/add")
    private ResponseEntity<Object> addAnimal(@RequestBody RoomsModel roomsModel) {
        return roomsService.addRoom(roomsModel);
    }

    @GetMapping("/get/{name}")
    private ResponseEntity<Object> getAnimal(@PathVariable("name") String name) {
        return roomsService.getRoomByName(name);
    }

    @DeleteMapping("/delete/{name}")
    private ResponseEntity<Object> deleteRoom(@PathVariable("name") String name) {
        return roomsService.deleteRoomByName(name);
    }

    @PutMapping("/update/{id}")
    private ResponseEntity<Object> updateAnimal(@RequestBody RoomsModel roomsModel, @PathVariable String id) {
        return roomsService.updateRoomById(roomsModel, id);
    }
}
