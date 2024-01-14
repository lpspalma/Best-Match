package com.bestmatch.BestMatch.dto;

import com.opencsv.bean.CsvBindByName;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CuisineDTO {
    @CsvBindByName(column = "id")
    private Long id;

    @Size(max = 50, message = "Cuisine Name too long, maximum 50 characters allowed")
    @CsvBindByName(column = "name")
    private String name;
}
