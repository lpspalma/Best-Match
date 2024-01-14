package com.bestmatch.BestMatch.service;

public interface DTOBuilder<T, U> {
    T build(U dto);
}
