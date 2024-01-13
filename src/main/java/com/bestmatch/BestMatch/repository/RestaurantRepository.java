package com.bestmatch.BestMatch.repository;

import com.bestmatch.BestMatch.model.Restaurant;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Optional<List<Restaurant>> findByCuisineNameContainingIgnoreCaseAndNameContainingIgnoreCase(String cuisineName, String restaurantName, Sort sort);

}
