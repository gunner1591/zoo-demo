package com.zoo.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(FavRooms.class)
@Table(name = "fav_rooms")
public class FavRooms implements Serializable {
    @Id
    @Column(name = "a_id")
    private String aid;

    @Id
    @Column(name = "r_id")
    private String rid;

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }
}
