package com.zoo.controller;

import com.zoo.model.AnimalsModel;
import com.zoo.service.AnimalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/animals")
public class AnimalsController {

    @Autowired
    private AnimalsService animalsService;

    @PostMapping("/add")
    private ResponseEntity<Object> addAnimal(@RequestBody AnimalsModel animalsModel) {
        return animalsService.addAnimal(animalsModel);
    }

    @GetMapping("/get/{title}")
    private ResponseEntity<Object> getAnimal(@PathVariable("title") String title) {
        return animalsService.getAnimalByTitle(title);
    }

    @DeleteMapping("/delete/{title}")
    private ResponseEntity<Object> deleteAnimal(@PathVariable("title") String title) {
        return animalsService.deleteAnimalByTitle(title);
    }

    @PutMapping("/update/{id}")
    private ResponseEntity<Object> updateAnimal(@RequestBody AnimalsModel animalsModel, @PathVariable String id) {
        return animalsService.updateAnimalById(animalsModel, id);
    }

    @PutMapping("/addtoroom/{id}")
    private ResponseEntity<Object> putAnimalInRoom(@PathVariable String id, @RequestParam String roomid) {
        return animalsService.putAnimalInRoom(id, roomid);
    }

    @PutMapping("/removefromroom/{id}")
    private ResponseEntity<Object> putAnimalInRoom(@PathVariable String id) {
        return animalsService.removeAnimalFromRoom(id);
    }

    @PostMapping("/assignfavroom")
    private ResponseEntity<Object> assignFavRoom(@RequestParam String aid, @RequestParam String roomid) {
        return animalsService.assignFavRoom(aid, roomid);
    }

    @DeleteMapping("/removefavroom")
    private ResponseEntity<Object> removeFavRoom(@RequestParam String aid, @RequestParam String roomid) {
        return animalsService.removeFavRoom(aid, roomid);
    }
}
