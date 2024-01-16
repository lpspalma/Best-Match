package com.bestmatch.BestMatch.service;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class ReadFileService {
    public <T> List<T> readCsv(String fileName, Class<T> clazz) {
        try (InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName)) {
            if (inputStream == null) {
                log.error("File not found in resources: " + fileName);
                return Collections.emptyList();
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            return new CsvToBeanBuilder<T>(reader)
                    .withType(clazz)
                    .build()
                    .parse();
        } catch (IOException e) {
            log.error("Failed to read file from resources. " + e);
            return Collections.emptyList();
        }
    }
}
