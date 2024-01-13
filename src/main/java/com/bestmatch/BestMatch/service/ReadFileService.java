package com.bestmatch.BestMatch.service;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class ReadFileService {
    public <T> List<T> readCsv(String fileName, Class<T> clazz) {
        try (FileReader fileReader = new FileReader(fileName)) {
            return new CsvToBeanBuilder<T>(fileReader)
                    .withType(clazz)
                    .build()
                    .parse();
        } catch (IOException e) {
            log.error("Failed to read file from resources. "+ e);
            return Collections.emptyList();
        }
    }
}
