package com.javaweb.model;

public class ListPoiDTO {
    private int id;                // POI ID
    private String name;           // POI Name
    private Integer orderintour;   // Thứ tự
    private int tourPoiId;         // ID bảng trung gian TourPoi

    public ListPoiDTO(int id, String name, Integer orderintour, int tourPoiId) {
        this.id = id;
        this.name = name;
        this.orderintour = orderintour;
        this.tourPoiId = tourPoiId;
    }

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

    public Integer getOrderintour() {
        return orderintour;
    }

    public void setOrderintour(Integer orderintour) {
        this.orderintour = orderintour;
    }

    public int getTourPoiId() {
        return tourPoiId;
    }

    public void setTourPoiId(int tourPoiId) {
        this.tourPoiId = tourPoiId;
    }
// Getters & Setters nếu cần
}
