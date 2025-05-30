package com.javaweb.repository.entity;

import javax.persistence.*;

@Entity
@Table(name = "tourtypetour")
public class TourTypeTourEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "tourid", nullable = false)
    private TourEntity tour;

    @ManyToOne
    @JoinColumn(name = "typeid", nullable = false)
    private TypeTourEntity typeTour;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TourEntity getTour() {
        return tour;
    }

    public void setTour(TourEntity tour) {
        this.tour = tour;
    }

    public TypeTourEntity getTypeTour() {
        return typeTour;
    }

    public void setTypeTour(TypeTourEntity typeTour) {
        this.typeTour = typeTour;
    }

}

