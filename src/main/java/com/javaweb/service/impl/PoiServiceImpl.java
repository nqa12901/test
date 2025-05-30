package com.javaweb.service.impl;

import com.javaweb.model.ListPoiDTO;
import com.javaweb.model.PoiDTO;
import com.javaweb.model.PoiTourDTO;
import com.javaweb.model.TourPoiRequestDTO;
import com.javaweb.repository.PoiRepository;
import com.javaweb.repository.TourPoiRepository;
import com.javaweb.repository.TourRepository;
import com.javaweb.repository.entity.PoiEntity;
import com.javaweb.repository.entity.TourEntity;
import com.javaweb.repository.entity.TourPoiEntity;
import com.javaweb.service.PoiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PoiServiceImpl implements PoiService {
    @Autowired
    private TourPoiRepository tourPoiRepository;
    @Autowired
    private TourRepository tourRepository;

    @Override
    public List<ListPoiDTO> getPoisByTourId(int id) {
        return tourPoiRepository.findPoiByTourId(id);
    }
     @Autowired
    private PoiRepository poiRepository;

    @Override
    public PoiDTO findById(int id) {
        return poiRepository.findPoiDetailById(id);
    }

    @Override
    @Transactional
    public void assignPoisToTour(TourPoiRequestDTO tourPoiRequestDTO) {
        TourEntity tour = tourRepository.findById(tourPoiRequestDTO.getTourId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Tour ID: " + tourPoiRequestDTO.getTourId()));

        tourPoiRepository.deleteAllByTourId(tourPoiRequestDTO.getTourId());
        for(PoiTourDTO poiDto:tourPoiRequestDTO.getPoiList()){
            PoiEntity poi = poiRepository.findById(poiDto.getPoiId())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy POI ID: " + poiDto.getPoiId()));

            TourPoiEntity tourpoi=new TourPoiEntity();
            tourpoi.setTour(tour);
            tourpoi.setPoi(poi);
            tourpoi.setOrderintour(poiDto.getOrderintour());;
            tourPoiRepository.save(tourpoi);
        }
    }



}
