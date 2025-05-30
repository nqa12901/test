package com.javaweb.builder;

import java.util.List;

public class TourSearchBuilder {
    private String typetour;
    private String address;
    private Integer price;
    private List<String> durations;
    private Integer rating;
    private TourSearchBuilder(Builder builder) {
        this.typetour = builder.typetour;
        this.address = builder.address;
        this.price = builder.price;
        this.rating = builder.rating;
        this.durations = builder.durations;
    }

    public String getTypetour() {
        return typetour;
    }

    public String getAddress() {
        return address;
    }

    public Integer getPrice() {
        return price;
    }

    public List<String> getDurations() {
        return durations;
    }

    public Integer getRating() {
        return rating;
    }
    public static class Builder {

        private String typetour;
        private String address;
        private Integer price;
        private List<String> durations;
        private Integer rating;

        public Builder setTypetour(String typeName) {
            this.typetour = typeName;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setPrice(Integer price) {
            this.price = price;
            return this;
        }

        public Builder setDurations(List<String> durations) {
            this.durations = durations;
            return this;
        }

        public Builder setRating(Integer rating) {
            this.rating = rating;
            return this;
        }
        public TourSearchBuilder build() {
            return new TourSearchBuilder(this);
        }
    }
}
