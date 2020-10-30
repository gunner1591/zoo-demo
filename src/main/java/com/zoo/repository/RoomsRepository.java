package com.zoo.repository;

import com.zoo.entity.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface RoomsRepository extends JpaRepository<Rooms, Long> {

    Optional<Rooms> findByName(String name);

    @Modifying
    @Transactional
    @Query("delete from Rooms r where r.name = ?1")
    void deleteByName(String name);

    Rooms findById(String id);
}
