package com.javaweb.repository;

import com.javaweb.builder.TourSearchBuilder;
import com.javaweb.repository.custom.TourRepositoryCustom;
import com.javaweb.repository.entity.TourEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface TourRepository extends JpaRepository<TourEntity,Integer> , TourRepositoryCustom {
    @Transactional
    List<TourEntity> findAll(TourSearchBuilder tourSearchBuilder);
    @Transactional
    void deleteTourById(int id);

}
