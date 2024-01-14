package com.bestmatch.BestMatch.service;

import com.bestmatch.BestMatch.dto.CuisineDTO;
import com.bestmatch.BestMatch.model.Cuisine;
import com.bestmatch.BestMatch.repository.CuisineRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CuisineServiceTest {
    @Mock
    private CuisineRepository cuisineRepository;

    @InjectMocks
    private CuisineService cuisineService;

    @Test
    void when_ReceivingValidDTO_Them_ReturnsCuisineEntitySuccessfully() {
        CuisineDTO dto = new CuisineDTO();
        dto.setId(1L);
        dto.setName("name");

        Cuisine cuisine = cuisineService.build(dto);

        assertAll(
                () -> assertEquals(1L, cuisine.getId()),
                () -> assertEquals("name", cuisine.getName())
        );
    }

    @Test
    void when_ReceivingValidID_Them_ReturnsCuisineEntitySuccessfully() {
        Cuisine cuisine = new Cuisine();
        cuisine.setId(1L);
        cuisine.setName("name");

        when(cuisineRepository.findById(1L)).thenReturn(Optional.of(cuisine));

        Cuisine response = cuisineService.findCuisineById(1L);

        assertAll(
                () -> assertEquals(1L, response.getId()),
                () -> assertEquals("name", response.getName())
        );
    }

    @Test
    void when_ReceivingInvalidID_Them_ReturnsNull() {
        when(cuisineRepository.findById(1L)).thenReturn(Optional.empty());

        assertNull(cuisineService.findCuisineById(1L));
    }
}
