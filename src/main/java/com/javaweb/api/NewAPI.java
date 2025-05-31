package com.javaweb.api;

import com.javaweb.model.ListPoiDTO;
import com.javaweb.model.PoiDTO;
import com.javaweb.model.TourDTO;
import com.javaweb.model.TourPoiRequestDTO;
import com.javaweb.repository.TourPoiRepository;
import com.javaweb.repository.TourRepository;
import com.javaweb.service.PoiService;
import com.javaweb.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
public class NewAPI {
    @Autowired
    private TourService tourService;
    @Autowired
    private TourRepository tourRepository;
    @Autowired
    private TourPoiRepository tourPoiRepository;

    @GetMapping(value = "api/tour")
    public List<TourDTO> findAll(@RequestParam Map<String, Object> params,
                                 @RequestParam(name = "duration", required = false) List<String> durations) {
        List<TourDTO> tours = tourService.findAll(params, durations);
        return tours;

    }
    @Autowired
    private PoiService poiService;
    @GetMapping(value="api/listtour/{id}")
    public List<ListPoiDTO> findlist(@PathVariable int id){
    return poiService.getPoisByTourId(id);}


    @GetMapping(value="api/poi/{id}")
    public PoiDTO findpoi(@PathVariable int id){
        return poiService.findById(id);
    }

    @DeleteMapping(value="api/tour/{id}")
    public void delete(@PathVariable int id){
        tourRepository.deleteTourById(id);
    }

    @DeleteMapping(value = "api/poioftour/{id}")
    public void deletepoioftour(@PathVariable int id){
        tourPoiRepository.deleteById(id);
    }



    @PostMapping(value="api/tour")
    public void saveOrUpdate(@RequestBody TourDTO dto) {
        tourService.saveOrUpdate(dto);
    }

    @PostMapping(value="api/assignpoi")
    public void assignPoisToTour(@RequestBody TourPoiRequestDTO tourPoiRequestDTO) {
        poiService.assignPoisToTour(tourPoiRequestDTO);
    }



}
