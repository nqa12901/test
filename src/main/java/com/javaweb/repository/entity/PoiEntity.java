package com.javaweb.repository.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="poi")
public class PoiEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="typename")
    private String typename;

    @Column(name="address")
    private String address;

    @Column(name="description")
    private String description;

    @Column(name = "imageUrl")
    private String imageUrl;

    @Column(name = "openTime")
    private String openTime;

    @Column(name = "closeTime")
    private String closeTime;

    @Column(name = "price")
    private Integer price;

    @OneToMany(mappedBy = "poi", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TourPoiEntity> tourpoiList;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public List<TourPoiEntity> getTourpoiList() {
        return tourpoiList;
    }

    public void setTourpoiList(List<TourPoiEntity> tourpoiList) {
        this.tourpoiList = tourpoiList;
    }
}
