package com.bestmatch.BestMatch.service;

import com.bestmatch.BestMatch.dto.CuisineDTO;
import com.bestmatch.BestMatch.model.Cuisine;
import com.bestmatch.BestMatch.repository.CuisineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CuisineService extends DTOBuilder<Cuisine, CuisineDTO>{
    @Autowired
    private CuisineRepository repository;

    public Cuisine findCuisineById(Long id){
        return repository.findById(id).orElse(null);
    }

    @Override
    public Cuisine build(CuisineDTO dto) {
        return Cuisine.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }
}
