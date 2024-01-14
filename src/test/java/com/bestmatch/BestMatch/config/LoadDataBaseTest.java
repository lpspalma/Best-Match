package com.bestmatch.BestMatch.config;

import com.bestmatch.BestMatch.dto.CuisineDTO;
import com.bestmatch.BestMatch.dto.RestaurantDTO;
import com.bestmatch.BestMatch.model.Cuisine;
import com.bestmatch.BestMatch.model.Restaurant;
import com.bestmatch.BestMatch.repository.CuisineRepository;
import com.bestmatch.BestMatch.repository.RestaurantRepository;
import com.bestmatch.BestMatch.service.CuisineService;
import com.bestmatch.BestMatch.service.ReadFileService;
import com.bestmatch.BestMatch.service.RestaurantService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class LoadDataBaseTest {
    @MockBean
    private ReadFileService readFileService;

    @MockBean
    private RestaurantService restaurantService;

    @MockBean
    private CuisineService cuisineService;

    @MockBean
    private RestaurantRepository restaurantRepository;

    @MockBean
    private CuisineRepository cuisineRepository;

    @Autowired
    private CommandLineRunner commandLineRunner;

    @Test
    void initDatabase_WhenNoDataInRepository_ShouldInitializeData() throws Exception {
        when(restaurantRepository.findAll()).thenReturn(Collections.emptyList());

        List<CuisineDTO> mockCuisines = Collections.singletonList(new CuisineDTO());
        List<RestaurantDTO> mockRestaurants = Collections.singletonList(RestaurantDTO.builder().build());
        when(readFileService.readCsv(any(), eq(CuisineDTO.class))).thenReturn(mockCuisines);
        when(readFileService.readCsv(any(), eq(RestaurantDTO.class))).thenReturn(mockRestaurants);
        when(cuisineService.build(any())).thenReturn(new Cuisine());
        when(restaurantService.build(any())).thenReturn(new Restaurant());

        commandLineRunner.run();

        verify(cuisineRepository, times(1)).save(any());
        verify(restaurantRepository, times(1)).save(any());
    }

}
