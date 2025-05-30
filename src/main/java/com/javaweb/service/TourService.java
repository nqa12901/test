package com.javaweb.service;

import com.javaweb.builder.TourSearchBuilder;
import com.javaweb.converter.TourSearchBuilderConverter;
import com.javaweb.model.TourDTO;

import java.util.List;
import java.util.Map;

public interface TourService {
    List<TourDTO> findAll(Map<String, Object> params, List<String> durations);
    void saveOrUpdate(TourDTO tourDTO);
}
