package com.bestmatch.BestMatch.service;

import org.springframework.stereotype.Service;

import java.util.List;
@Service
public abstract class FilterService<T>{
    abstract List<T> filterDistanceRatingAndPrice(String distance, String rating, String price, List<T> list);
}
