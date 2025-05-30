package com.javaweb.model;

public class ListPoiDTO {
    private int id;
    private String name;
    private Integer orderintour;
    public ListPoiDTO(int id, String name, Integer orderintour) {
        this.id = id;
        this.name = name;
        this.orderintour = orderintour;
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
}
