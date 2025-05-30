package com.javaweb.converter;

import com.javaweb.builder.TourSearchBuilder;
import com.javaweb.utils.MapUtil;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
// thong bao day la bean
@Component
public class TourSearchBuilderConverter {
    public TourSearchBuilder toTourSearchBuilder(Map<String, Object> params, List<String> durations) {
        TourSearchBuilder tourSearchBuilder = new TourSearchBuilder.Builder()
                .setTypetour(MapUtil.getObject(params, "typetour", String.class))
                .setAddress(MapUtil.getObject(params, "address", String.class))
                .setPrice(MapUtil.getObject(params, "price", Integer.class))
                .setDurations(durations)
                .setRating(MapUtil.getObject(params, "rating", Integer.class))
                .build();

        return tourSearchBuilder;

    }
}
