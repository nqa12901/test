package com.javaweb.repository.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="typetour")
public class TypeTourEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "typeTour", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TourTypeTourEntity> tourTypeTours ;

    // Getter & Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<TourTypeTourEntity> getTourTypeTours() {
        return tourTypeTours;
    }

    public void setTourTypeTours(List<TourTypeTourEntity> tourTypeTours) {
        this.tourTypeTours = tourTypeTours;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
