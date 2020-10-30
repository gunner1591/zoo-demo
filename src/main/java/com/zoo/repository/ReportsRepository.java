package com.zoo.repository;

import com.zoo.dto.AnimalsDTO;
import com.zoo.dto.RoomsDTO;
import com.zoo.entity.Animals;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportsRepository extends JpaRepository<Animals, Long> {

    @Query("select new com.zoo.dto.AnimalsDTO(a.title, a.dateAdded) from Animals a where a.roomId IS NULL")
    List<AnimalsDTO> getSortedList(Sort sort);

    @Query("select new com.zoo.dto.AnimalsDTO(a.title, a.dateAdded) from Animals a where a.roomId = ?1")
    List<AnimalsDTO> getSortedListAnimals(String room, Sort sort);

    @Query("select new com.zoo.dto.RoomsDTO(r.name) from Rooms r where r.id in (select f.rid from FavRooms f where  f.aid = ?1)")
    List<RoomsDTO> getFavRoomsList(String id);
}
