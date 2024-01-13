package com.bestmatch.BestMatch.service;

import com.bestmatch.BestMatch.dto.RestaurantDTO;
import com.bestmatch.BestMatch.model.Restaurant;
import com.bestmatch.BestMatch.repository.RestaurantRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class RestaurantService extends DTOBuilder<Restaurant, RestaurantDTO>{
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private CuisineService cuisineService;

    @Autowired
    private ReadFileService readFileService;

    /*public boolean readRestaurantFromCSV(String filename) {
        boolean result = false;
        List<RestaurantDTO> restaurants = readFileService.readCsv(filename, RestaurantDTO.class);
        if (!restaurants.isEmpty()) {
            restaurants.forEach(r -> restaurantRepository.save(build(r)));
            result = true;
        }
        return result;
    }*/

    @Override
    public Restaurant build(RestaurantDTO dto) {
        return Restaurant.builder()
                .name(dto.getName())
                .rating(Integer.parseInt(dto.getRating()))
                .distance(Integer.parseInt(dto.getDistance()))
                .price(Integer.parseInt(dto.getPrice()))
                .cuisine(cuisineService.findCuisineById(Long.parseLong(dto.getCuisineId())))
                .build();
    }
}
