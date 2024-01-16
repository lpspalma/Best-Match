package com.bestmatch.BestMatch.dto;

import com.opencsv.bean.CsvBindByName;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDTO {
    @Size(max = 50, message = "Name too long, maximum 50 characters allowed")
    @CsvBindByName(column = "name")
    private String restaurantName;

    @Pattern(regexp = "^$|[1-5]", message = "Wrong Format, Rating should be values between 1 and 5")
    @CsvBindByName(column = "customer_rating")
    private String rating;

    @Pattern(regexp = "^$|\\d+", message = "Wrong Format - Only accept numbers")
    @CsvBindByName(column = "distance")
    private String distance;

    @Pattern(regexp = "^$|\\d+", message = "Wrong Format - Only accept numbers")
    @CsvBindByName(column = "price")
    private String price;

    @Size(max = 50, message = "Cuisine Name too long, maximum 50 characters allowed")
    @CsvBindByName(column = "cuisine_id")
    private String cuisine;
}
