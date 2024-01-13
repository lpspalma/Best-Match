package com.bestmatch.BestMatch.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class CuisineDTO {
    @CsvBindByName(column = "id")
    private Long id;

    @CsvBindByName(column = "name")
    private String name;
}
