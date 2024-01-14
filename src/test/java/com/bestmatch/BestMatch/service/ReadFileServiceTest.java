package com.bestmatch.BestMatch.service;

import com.bestmatch.BestMatch.dto.CuisineDTO;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReadFileServiceTest {
    ReadFileService readFileService = new ReadFileService();

    @Test
    void when_ReadCSVReadFileSuccessfully_Them_ReturnsFilledList() {
        String fileName = "src/test/java/resources/test.csv";
        CuisineDTO dto = new CuisineDTO();
        dto.setId(3L);
        dto.setName("Thai");

        List<CuisineDTO> result = readFileService.readCsv(fileName, CuisineDTO.class);

        assertAll(
                () -> assertNotNull(result),
                () -> assertFalse(result.isEmpty()),
                () -> assertTrue(result.contains(dto))
        );
    }

    @Test
    void when_ReadCSVInvalidPath_Them_ReturnsEmptyList() {
        String fileName = "anyOtherPath";

        List<CuisineDTO> result = readFileService.readCsv(fileName, CuisineDTO.class);

        assertAll(
                () -> assertNotNull(result),
                () -> assertTrue(result.isEmpty())
        );
    }

    @Test
    void when_ReadCSVReceivesInvalidFile_Them_ReturnsEmptyList() {
        String fileName = "src/test/java/resources/empty.csv";

        List<CuisineDTO> result = readFileService.readCsv(fileName, CuisineDTO.class);

        assertAll(
                () -> assertNotNull(result),
                () -> assertTrue(result.isEmpty())
        );
    }
}
