package com.javaweb.repository;

import com.javaweb.model.ListPoiDTO;
import com.javaweb.repository.entity.TourPoiEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TourPoiRepository extends JpaRepository <TourPoiEntity,Integer>{
    @Query("SELECT new com.javaweb.model.ListPoiDTO(p.id, p.name, tp.orderintour, tp.id) " +
            "FROM TourPoiEntity tp " +
            "JOIN tp.poi p " +
            "WHERE tp.tour.id = :id " +
            "ORDER BY tp.orderintour ASC")
    List<ListPoiDTO> findPoiByTourId(@Param("id") int tourId);

    void deletePoiByTourId(int tourid);

    void deleteAllByTourId(Integer tourId);
}
