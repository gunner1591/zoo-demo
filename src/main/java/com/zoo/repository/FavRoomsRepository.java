package com.zoo.repository;

import com.zoo.entity.FavRooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface FavRoomsRepository extends JpaRepository<FavRooms, Long> {

    @Modifying
    @Transactional
    @Query("delete from FavRooms f where f.aid = ?1 and f.rid = ?2")
    void deleteFavRoom(String aid, String roomid);

}
