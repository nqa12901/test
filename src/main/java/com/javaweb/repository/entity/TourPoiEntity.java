package com.javaweb.repository.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tourpoi")
public class TourPoiEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "orderintour")

    private int orderintour;
    @ManyToOne
    @JoinColumn(name = "tourid", nullable = false)
    private TourEntity tour;

    @ManyToOne
    @JoinColumn(name = "poiid", nullable = false)
    private PoiEntity poi;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderintour() {
        return orderintour;
    }

    public void setOrderintour(int orderintour) {
        this.orderintour = orderintour;
    }

    public PoiEntity getPoi() {
        return poi;
    }

    public void setPoi(PoiEntity poi) {
        this.poi = poi;
    }

    public TourEntity getTour() {
        return tour;
    }

    public void setTour(TourEntity tour) {
        this.tour = tour;
    }
}
