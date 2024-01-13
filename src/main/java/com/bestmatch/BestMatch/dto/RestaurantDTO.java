package com.bestmatch.BestMatch.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class RestaurantDTO {
    @CsvBindByName(column = "name")
    private String name;

    @CsvBindByName(column = "customer_rating")
    private String rating;

    @CsvBindByName(column = "distance")
    private String distance;

    @CsvBindByName(column = "price")
    private String price;

    @CsvBindByName(column = "cuisine_id")
    private String cuisineId;
}
