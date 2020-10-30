package com.zoo.service;

import com.zoo.dto.AnimalsDTO;
import com.zoo.dto.RoomsDTO;
import com.zoo.repository.ReportsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportsService {

    @Autowired
    ReportsRepository reportsRepository;

    public ResponseEntity getAnimalsWithoutRooms(String column, String order){
        List<AnimalsDTO> animals = reportsRepository.getSortedList(Sort.by(Sort.Direction.fromString(order),column));
        return ResponseEntity.ok(animals);
    }

    public ResponseEntity getAnimalsInRoom(String room, String column, String order){
        List<AnimalsDTO> animals = reportsRepository.getSortedListAnimals(room, (Sort.by(Sort.Direction.fromString(order),column)));
        return ResponseEntity.ok(animals);
    }

    public ResponseEntity getFavRoomsList(String id){
        List<RoomsDTO> rooms = reportsRepository.getFavRoomsList(id);
        return ResponseEntity.ok(rooms);
    }
}
