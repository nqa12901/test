package com.javaweb.model;

import java.util.List;

public class TourPoiRequestDTO {
    private Integer tourId;
    private List<PoiTourDTO> poiList;

    public Integer getTourId() {
        return tourId;
    }

    public void setTourId(Integer tourId) {
        this.tourId = tourId;
    }

    public List<PoiTourDTO> getPoiList() {
        return poiList;
    }

    public void setPoiList(List<PoiTourDTO> poiList) {
        this.poiList = poiList;
    }
}

