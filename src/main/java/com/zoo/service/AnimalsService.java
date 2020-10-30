package com.zoo.service;

import com.zoo.entity.Animals;
import com.zoo.entity.FavRooms;
import com.zoo.model.AnimalsModel;
import com.zoo.repository.AnimalsRepository;
import com.zoo.repository.FavRoomsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Objects;

@Service
public class AnimalsService {

    @Autowired
    private AnimalsRepository animalsRepository;

    @Autowired
    private FavRoomsRepository favRoomsRepository;

    public ResponseEntity addAnimal(AnimalsModel animalsModel) {
        if (animalsRepository.findByTitle(animalsModel.getTitle()).isPresent()) {
            return ResponseEntity.badRequest().body("Animal title is not unique. Failed to add animal.");
        } else {
            Animals animals = new Animals();
            animals.setTitle(animalsModel.getTitle());
            animals.setSpecies(animalsModel.getSpecies());
            animals.setSex(animalsModel.getSex());
            if (animalsModel.getDateAdded() != null) {
                animals.setDateAdded(LocalDate.parse(animalsModel.getDateAdded()));
            } else {
                animals.setDateAdded(LocalDate.now());
            }
            animalsRepository.save(animals);
            return ResponseEntity.ok("Animal added successfully");
        }
    }

    public ResponseEntity getAnimalByTitle(String title) {
        if (animalsRepository.findByTitle(title).isPresent()) {
            return ResponseEntity.ok(animalsRepository.findByTitle(title));
        } else {
            return ResponseEntity.badRequest().body("Animal not found");
        }
    }

    public ResponseEntity deleteAnimalByTitle(String title) {
        if (animalsRepository.findByTitle(title).isPresent()) {
            animalsRepository.deleteByTitle(title);
            return ResponseEntity.ok("Deleted " + title + " successfully");
        } else {
            return ResponseEntity.badRequest().body("Animal not found");
        }
    }

    public ResponseEntity updateAnimalById(AnimalsModel animalsModel, String id) {
        Animals animals = animalsRepository.findById(id);
        if (Objects.nonNull(animals)) {
            if (animalsModel.getTitle() != null) {
                animals.setTitle(animalsModel.getTitle());
            }
            if (animalsModel.getSpecies() != null) {
                animals.setSpecies(animalsModel.getSpecies());
            }
            if (animalsModel.getSex() != null) {
                animals.setSex(animalsModel.getSex());
            }
            if (animalsModel.getDateAdded() != null) {
                animals.setDateAdded(LocalDate.parse(animalsModel.getDateAdded()));
            }
            animalsRepository.save(animals);
            return ResponseEntity.ok("Animal updated successfully");
        } else {
            return ResponseEntity.badRequest().body("Animal not found");
        }
    }

    public ResponseEntity putAnimalInRoom(String id, String roomid) {
        Animals animals = animalsRepository.findById(id);
        if (Objects.nonNull(animals)) {
            animals.setRoomId(roomid);
            try {
                animalsRepository.save(animals);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Constraint Violation");
            }
            return ResponseEntity.ok("Animal added to room");
        } else {
            return ResponseEntity.badRequest().body("Animal not found");
        }
    }

    public ResponseEntity removeAnimalFromRoom(String id) {
        Animals animals = animalsRepository.findById(id);
        if (Objects.nonNull(animals)) {
            if (animals.getRoomId() != null) {
                animals.setRoomId(null);
                animalsRepository.save(animals);
                return ResponseEntity.ok("Animal removed from room");
            } else {
                return ResponseEntity.badRequest().body("Animal is not in any room");
            }
        } else {
            return ResponseEntity.badRequest().body("Animal not found");
        }
    }

    public ResponseEntity assignFavRoom(String aid, String roomid) {
        FavRooms favRooms = new FavRooms();
        favRooms.setAid(aid);
        favRooms.setRid(roomid);
        try {
            favRoomsRepository.save(favRooms);
            return ResponseEntity.ok("Favorite room added for Animal");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Constraint Violation");
        }
    }

    public ResponseEntity removeFavRoom(String aid, String roomid) {
        try {
            favRoomsRepository.deleteFavRoom(aid, roomid);
            return ResponseEntity.ok("Deleted favorite room successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Constraint Violation");
        }
    }
}
