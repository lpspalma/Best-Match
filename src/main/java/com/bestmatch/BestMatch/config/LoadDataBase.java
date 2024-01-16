package com.bestmatch.BestMatch.config;

import com.bestmatch.BestMatch.dto.CuisineDTO;
import com.bestmatch.BestMatch.dto.RestaurantDTO;
import com.bestmatch.BestMatch.repository.CuisineRepository;
import com.bestmatch.BestMatch.repository.RestaurantRepository;
import com.bestmatch.BestMatch.service.CuisineService;
import com.bestmatch.BestMatch.service.ReadFileService;
import com.bestmatch.BestMatch.service.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Slf4j
@Configuration
public class LoadDataBase {
    private static final String PRELOADING = "Preloading ";

    @Value("${preload.cuisinesCsvFile:}")
    private String cuisinesCsvPath;

    @Value("${preload.restaurantsCsvFile:}")
    private String restaurantsCsvPath;

    @Bean
    CommandLineRunner initDatabase(ReadFileService readFileService, RestaurantService restaurantService, CuisineService cuisineService, RestaurantRepository restaurantRepository, CuisineRepository cuisineRepository) {
        return args -> {
            if (restaurantRepository.findAll().isEmpty()) {
                List<CuisineDTO> cuisines = readFileService.readCsv(cuisinesCsvPath, CuisineDTO.class);
                List<RestaurantDTO> restaurants = readFileService.readCsv(restaurantsCsvPath, RestaurantDTO.class);
                if(!cuisines.isEmpty() && !restaurants.isEmpty()){
                    cuisines.forEach(c ->  log.info("{}{}", PRELOADING, cuisineRepository.save(cuisineService.build(c))));
                    restaurants.forEach(r -> log.info("{}{}", PRELOADING, restaurantRepository.save(restaurantService.build(r))));
                }
            }
        };
    }
}
