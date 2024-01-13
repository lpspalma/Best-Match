package com.bestmatch.BestMatch;

import com.bestmatch.BestMatch.model.Cuisine;
import com.bestmatch.BestMatch.model.Restaurant;
import com.bestmatch.BestMatch.service.ReadFileService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class FilterTest {
    private final ReadFileService readFileService = new ReadFileService();
    @Test
    void test(){
        Cuisine cuisine = Cuisine.builder().id(1L).name("cuisine").build();
        Restaurant restaurant = Restaurant.builder()
                .name("sdagsd")
                .rating(5)
                .price(10)
                .distance(2)
                .cuisine(cuisine)
                .build();
        System.out.println(restaurant);
    }

}
