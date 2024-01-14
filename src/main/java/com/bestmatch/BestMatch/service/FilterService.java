package com.bestmatch.BestMatch.service;

import java.util.List;

public abstract class FilterService<T>{
    abstract List<T> filterDistanceRatingAndPrice(String distance, String rating, String price, List<T> list);
}
