package com.javaweb.service;

import com.javaweb.model.ListPoiDTO;
import com.javaweb.model.PoiDTO;
import com.javaweb.model.TourPoiRequestDTO;

import java.util.List;

public interface PoiService {
    List<ListPoiDTO> getPoisByTourId(int id);
    PoiDTO findById(int id);
    void assignPoisToTour(TourPoiRequestDTO tourPoiRequestDTO );
    void saveorupdate(PoiDTO poiDTO);
}
