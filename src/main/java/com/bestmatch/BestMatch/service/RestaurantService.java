package com.bestmatch.BestMatch.service;

import com.bestmatch.BestMatch.dto.RestaurantDTO;
import com.bestmatch.BestMatch.model.Restaurant;
import com.bestmatch.BestMatch.repository.RestaurantRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RestaurantService extends DTOBuilder<Restaurant, RestaurantDTO> {
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private CuisineService cuisineService;

    @Override
    public Restaurant build(RestaurantDTO dto) {
        return Restaurant.builder()
                .name(dto.getName())
                .rating(Integer.parseInt(dto.getRating()))
                .distance(Integer.parseInt(dto.getDistance()))
                .price(Integer.parseInt(dto.getPrice()))
                .cuisine(cuisineService.findCuisineById(Long.parseLong(dto.getCuisine())))
                .build();
    }

    public List<RestaurantDTO> findBestMatch(RestaurantDTO dto) {
        List<Restaurant> restaurants =
                restaurantRepository.findByCuisineNameContainingIgnoreCaseAndNameContainingIgnoreCase(dto.getCuisine(), dto.getName(), Sort.by("distance")).orElse(Collections.emptyList());
        return restaurants.stream()
                .filter(restaurant ->
                        restaurant.getDistance() <= (dto.getDistance().isEmpty() ? 10 : Integer.parseInt(dto.getDistance())) &&
                                restaurant.getRating() >= (dto.getRating().isEmpty() ? 0 : Integer.parseInt(dto.getRating())) &&
                                restaurant.getPrice() <= (dto.getPrice().isEmpty() ? 50 : Integer.parseInt(dto.getPrice())))
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private RestaurantDTO convertToDTO(Restaurant restaurant) {
        RestaurantDTO restaurantDTO = new RestaurantDTO();
        restaurantDTO.setName(restaurant.getName());
        restaurantDTO.setRating(String.valueOf(restaurant.getRating()));
        restaurantDTO.setDistance(String.valueOf(restaurant.getDistance()));
        restaurantDTO.setPrice(String.valueOf(restaurant.getPrice()));
        restaurantDTO.setCuisine(restaurant.getCuisine().getName());
        return restaurantDTO;
    }
}
