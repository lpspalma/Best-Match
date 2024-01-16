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

@Slf4j
@Service
public class RestaurantService extends FilterService<Restaurant> implements DTOBuilder<Restaurant, RestaurantDTO> {
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private CuisineService cuisineService;

    public List<RestaurantDTO> findBestMatch(RestaurantDTO dto) {
        log.info("Parameters received: " + dto);
        List<Restaurant> restaurants =
                restaurantRepository
                        .findByCuisineNameContainingIgnoreCaseAndNameContainingIgnoreCase(
                                dto.getCuisine(),
                                dto.getRestaurantName(),
                                Sort.by("distance", "rating", "price"))
                        .orElse(Collections.emptyList());
        restaurants = filterDistanceRatingAndPrice(dto.getDistance(), dto.getRating(), dto.getPrice(), restaurants);
        return restaurants.stream().limit(5).map(this::convertToDTO).toList();
    }

    @Override
    public Restaurant build(RestaurantDTO dto) {
        return Restaurant.builder()
                .name(dto.getRestaurantName())
                .rating(Integer.parseInt(dto.getRating()))
                .distance(Integer.parseInt(dto.getDistance()))
                .price(Integer.parseInt(dto.getPrice()))
                .cuisine(cuisineService.findCuisineById(Long.parseLong(dto.getCuisine())))
                .build();
    }

    @Override
    List<Restaurant> filterDistanceRatingAndPrice(String distance, String rating, String price, List<Restaurant> list) {
        return list.stream()
                .filter(restaurant ->
                        restaurant.getDistance() <= (distance.isEmpty() ? 10 : Integer.parseInt(distance)) &&
                                restaurant.getRating() >= (rating.isEmpty() ? 0 : Integer.parseInt(rating)) &&
                                restaurant.getPrice() <= (price.isEmpty() ? 50 : Integer.parseInt(price)))
                .toList();
    }

    private RestaurantDTO convertToDTO(Restaurant restaurant) {
        return RestaurantDTO.builder()
                .restaurantName(restaurant.getName())
                .rating(String.valueOf(restaurant.getRating()))
                .distance(String.valueOf(restaurant.getDistance()))
                .price(String.valueOf(restaurant.getPrice()))
                .cuisine(restaurant.getCuisine().getName())
                .build();
    }
}
