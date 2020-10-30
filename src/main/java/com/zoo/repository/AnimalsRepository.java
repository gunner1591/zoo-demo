package com.zoo.repository;

import com.zoo.entity.Animals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface AnimalsRepository extends JpaRepository<Animals, Long> {
    Optional<Animals> findByTitle(String title);

    @Modifying
    @Transactional
    @Query("delete from Animals a where a.title = ?1")
    void deleteByTitle(String title);

    Animals findById(String id);
}
