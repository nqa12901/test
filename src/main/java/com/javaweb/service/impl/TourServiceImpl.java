package com.javaweb.service.impl;

import com.javaweb.builder.TourSearchBuilder;
import com.javaweb.converter.TourSearchBuilderConverter;
import com.javaweb.model.TourDTO;
import com.javaweb.repository.TourRepository;
import com.javaweb.repository.entity.TourEntity;
import com.javaweb.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Service
public class TourServiceImpl implements TourService {
    @Autowired
    private TourRepository tourRepository;
    @Autowired
    private TourSearchBuilderConverter tourSearchBuilderConverter;
    @Override
    public List<TourDTO> findAll(Map<String, Object> params, List<String> durations) {
        TourSearchBuilder tourSearchBuilder = tourSearchBuilderConverter.toTourSearchBuilder(params, durations);
        List<TourEntity> tourEntities=tourRepository.findAll(tourSearchBuilder);
        List<TourDTO> result = new ArrayList<>();
        for (TourEntity entity : tourEntities) {
            TourDTO dto = new TourDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setAddress(entity.getAddress());
            dto.setDescription(entity.getDescription());
            dto.setPrice(entity.getPrice());
            dto.setDuration(entity.getDuration());
            dto.setImageUrl(entity.getImageUrl());
            dto.setRating(entity.getRating());
            result.add(dto);
        }



        return result;
    }
    @Autowired
    private TourRepository TourRepository;
    public void saveOrUpdate(TourDTO tourDTO){
        TourEntity tourEntity;
        if(tourDTO.getId() != null){
            tourEntity=tourRepository.findById(tourDTO.getId()).get();
        }
        else{
            tourEntity=new TourEntity();
        }
        tourEntity.setName(tourDTO.getName());
        tourEntity.setAddress(tourDTO.getAddress());
        tourEntity.setDescription(tourDTO.getDescription());
        tourEntity.setPrice(tourDTO.getPrice());
        tourEntity.setDuration(tourDTO.getDuration());
        tourEntity.setImageUrl(tourDTO.getImageUrl());
        tourEntity.setRating(tourDTO.getRating());
        tourRepository.save(tourEntity);

    }
}
