package com.bestmatch.BestMatch.controller;

import com.bestmatch.BestMatch.dto.RestaurantDTO;
import com.bestmatch.BestMatch.service.RestaurantService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("/findMatch")
    ResponseEntity<?> findBestMatch(@Valid @RequestBody RestaurantDTO dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return handleMethodArgumentNotValid(bindingResult);
        }
        List<RestaurantDTO> matches = restaurantService.findBestMatch(dto);
        if (matches == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(matches, HttpStatus.OK);
    }

    private ResponseEntity<List<String>> handleMethodArgumentNotValid(BindingResult bindingResult) {
        List<String> errors = bindingResult.getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
