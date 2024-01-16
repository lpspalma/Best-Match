package com.bestmatch.BestMatch.service;

import com.bestmatch.BestMatch.dto.RestaurantDTO;
import com.bestmatch.BestMatch.model.Cuisine;
import com.bestmatch.BestMatch.model.Restaurant;
import com.bestmatch.BestMatch.repository.RestaurantRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RestaurantServiceTest {
    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private CuisineService cuisineService;

    @InjectMocks
    private RestaurantService restaurantService;

    private static Restaurant restaurant1;
    private static Restaurant restaurant2;
    private static RestaurantDTO dto;
    private static Cuisine cuisine1;

    @BeforeAll
    static void init() {
        cuisine1 = Cuisine.builder().id(1L).name("cuisine1").build();
        Cuisine cuisine2 = Cuisine.builder().id(2L).name("cuisine2").build();
        restaurant1 = Restaurant.builder()
                .name("restaurant1")
                .rating(5)
                .price(10)
                .distance(2)
                .cuisine(cuisine1)
                .build();
        restaurant2 = Restaurant.builder()
                .name("restaurant2")
                .rating(5)
                .price(10)
                .distance(2)
                .cuisine(cuisine2)
                .build();
        dto = RestaurantDTO.builder()
                .restaurantName("restaurant1")
                .rating("5")
                .price("10")
                .distance("2")
                .cuisine(cuisine1.getName()).build();
    }

    @Test
    void when_FindBestMatchFindAMatch_them_ReturnsNotNullList() {
        List<Restaurant> restaurants = Collections.singletonList(restaurant2);
        when(restaurantRepository.findByCuisineNameContainingIgnoreCaseAndNameContainingIgnoreCase(anyString(), anyString(), any()))
                .thenReturn(Optional.of(restaurants));

        List<RestaurantDTO> response = restaurantService.findBestMatch(dto);

        assertAll(
                () -> assertFalse(response.isEmpty()),
                () -> assertEquals(1, response.size())
        );
    }

    @Test
    void when_FindBestMatchDoesNotFindAnyMatch_them_ReturnsEmptyList() {
        List<Restaurant> restaurants = Collections.emptyList();
        when(restaurantRepository.findByCuisineNameContainingIgnoreCaseAndNameContainingIgnoreCase(anyString(), anyString(), any()))
                .thenReturn(Optional.of(restaurants));

        List<RestaurantDTO> response = restaurantService.findBestMatch(dto);

        assertTrue(response.isEmpty());
    }

    @Test
    void when_FindBestMatchEmptyStringValue_them_ReturnsNotListWithMoreThanOneResult() {
        List<Restaurant> restaurants = Arrays.asList(restaurant1, restaurant2);
        when(restaurantRepository.findByCuisineNameContainingIgnoreCaseAndNameContainingIgnoreCase(anyString(), anyString(), any()))
                .thenReturn(Optional.of(restaurants));

        List<RestaurantDTO> response = restaurantService.findBestMatch(dto);

        assertAll(
                () -> assertFalse(response.isEmpty()),
                () -> assertEquals(2, response.size())
        );
    }

    @Test
    void when_ReceivingValidDTO_Them_ReturnsRestaurantEntitySuccessfully() {
        when(cuisineService.findCuisineById(any())).thenReturn(cuisine1);
        dto.setCuisine("1");

        Restaurant restaurant = restaurantService.build(dto);

        assertEquals(restaurant, restaurant1);
    }

}
