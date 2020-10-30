package com.zoo.service;

import com.zoo.entity.Rooms;
import com.zoo.model.RoomsModel;
import com.zoo.repository.RoomsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class RoomsService {

    @Autowired
    RoomsRepository roomsRepository;

    public ResponseEntity addRoom(RoomsModel roomsModel) {
        if (roomsRepository.findByName(roomsModel.getName()).isPresent()) {
            return ResponseEntity.badRequest().body("Room title is not unique. Failed to add room.");
        } else {
            Rooms room = new Rooms();
            room.setName(roomsModel.getName());
            room.setDescription(roomsModel.getDescription());
            room.setType(roomsModel.getType());
            roomsRepository.save(room);
            return ResponseEntity.ok("Room added successfully");
        }
    }

    public ResponseEntity getRoomByName(String name) {
        if (roomsRepository.findByName(name).isPresent()) {
            return ResponseEntity.ok(roomsRepository.findByName(name));
        } else {
            return ResponseEntity.badRequest().body("Room not found");
        }
    }

    public ResponseEntity deleteRoomByName(String name) {
        if (roomsRepository.findByName(name).isPresent()) {
            roomsRepository.deleteByName(name);
            return ResponseEntity.ok("Deleted " + name + " successfully");
        } else {
            return ResponseEntity.badRequest().body("Room not found");
        }
    }

    public ResponseEntity updateRoomById(RoomsModel roomsModel, String id) {
        Rooms room = roomsRepository.findById(id);
        if (Objects.nonNull(room)) {
            if (roomsModel.getName() != null) {
                room.setName(roomsModel.getName());
            }
            if (roomsModel.getDescription() != null) {
                room.setDescription(roomsModel.getDescription());
            }
            if (roomsModel.getType() != null) {
                room.setType(roomsModel.getType());
            }
            roomsRepository.save(room);
            return ResponseEntity.ok("Room details updated successfully");
        } else {
            return ResponseEntity.badRequest().body("Room not found");
        }
    }
}
