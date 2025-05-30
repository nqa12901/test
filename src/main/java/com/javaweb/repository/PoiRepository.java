package com.javaweb.repository;

import com.javaweb.model.PoiDTO;
import com.javaweb.repository.entity.PoiEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PoiRepository extends JpaRepository<PoiEntity, Integer> {

    @Query("SELECT new com.javaweb.model.PoiDTO(p.id, p.name, p.typename, p.address, " +
            "p.description, p.imageUrl, p.openTime, p.closeTime, p.price) " +
            "FROM PoiEntity p WHERE p.id = :id")
    PoiDTO findPoiDetailById(@Param("id") int id);



}
