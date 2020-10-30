package com.zoo.dto;

import java.time.LocalDate;

public class AnimalsDTO {
    private String title;

    private LocalDate dateAdded;

    public AnimalsDTO(String title, LocalDate dateAdded) {
        this.title = title;
        this.dateAdded = dateAdded;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
