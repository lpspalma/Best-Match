package com.bestmatch.BestMatch.service;

import org.springframework.stereotype.Service;

@Service
public interface DTOBuilder<T, U> {
    T build(U dto);
}
