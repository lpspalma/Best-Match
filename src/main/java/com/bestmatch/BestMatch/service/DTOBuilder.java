package com.bestmatch.BestMatch.service;

public abstract class DTOBuilder<T, U> {
    public abstract T build(U dto);
}
