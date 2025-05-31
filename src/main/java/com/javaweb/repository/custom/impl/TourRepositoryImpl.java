package com.javaweb.repository.custom.impl;

import com.javaweb.builder.TourSearchBuilder;
import com.javaweb.repository.TourRepository;
import com.javaweb.repository.custom.TourRepositoryCustom;
import com.javaweb.repository.entity.TourEntity;
import com.javaweb.repository.entity.TourTypeTourEntity;
import com.javaweb.repository.entity.TypeTourEntity;
import com.javaweb.utils.StringUtil;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Primary
public class TourRepositoryImpl implements TourRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<TourEntity> findAll(TourSearchBuilder builder) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<TourEntity> query = cb.createQuery(TourEntity.class);
        Root<TourEntity> root = query.from(TourEntity.class);
        List<Predicate> predicates = new ArrayList<>();

        // JOIN TourTypeTour → TypeTour nếu có typetour
        if (StringUtil.checkString(builder.getTypetour())) {
            Join<TourEntity, TourTypeTourEntity> tourTypeJoin = root.join("tourTypeTours", JoinType.LEFT);
            Join<TourTypeTourEntity, TypeTourEntity> typeTourJoin = tourTypeJoin.join("typeTour", JoinType.LEFT);
            predicates.add(cb.like(cb.lower(typeTourJoin.get("name")), "%" + builder.getTypetour().toLowerCase() + "%"));


        }
        System.out.println("Typetour filter: " + builder.getTypetour());


        if (StringUtil.checkString(builder.getAddress())) {
            predicates.add(cb.like(cb.lower(root.get("address")), "%" + builder.getAddress().toLowerCase() + "%"));
        }

        if (builder.getRating() != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("rating"), builder.getRating()));
        }

        if (builder.getPrice() != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("price"), builder.getPrice()));
        }

        if (builder.getDurations() != null && !builder.getDurations().isEmpty()) {
            List<Predicate> durationPredicates = new ArrayList<>();
            for (String duration : builder.getDurations()) {
                durationPredicates.add(cb.like(cb.lower(root.get("duration")), "%" + duration.toLowerCase() + "%"));
            }
            predicates.add(cb.or(durationPredicates.toArray(new Predicate[0])));
        }

        query.select(root).where(cb.and(predicates.toArray(new Predicate[0]))).distinct(true);
        return entityManager.createQuery(query).getResultList();
    }
    @Override
    @Transactional
    public void deleteTourById(Integer id) {
        // Xóa liên kết bảng phụ
        entityManager.createQuery("DELETE FROM TourTypeTourEntity ttt WHERE ttt.tour.id = :id")
                .setParameter("id", id).executeUpdate();
        entityManager.createQuery("DELETE FROM TourPoiEntity tp WHERE tp.tour.id = :id")
                .setParameter("id", id).executeUpdate();

        // Sau đó mới xóa tour
        TourEntity tour = entityManager.find(TourEntity.class, id);
        if (tour != null) {
            entityManager.remove(tour);
        }
    }


}
