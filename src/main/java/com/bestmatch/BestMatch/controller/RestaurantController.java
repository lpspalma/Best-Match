package com.bestmatch.BestMatch.controller;

import com.bestmatch.BestMatch.dto.RestaurantDTO;
import com.bestmatch.BestMatch.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("/findMatch")
    ResponseEntity<List<RestaurantDTO>> findBestMatch(@RequestBody RestaurantDTO dto){
        List<RestaurantDTO> matches = restaurantService.findBestMatch(dto);
        if(matches == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(matches, HttpStatus.OK);
    }
}
