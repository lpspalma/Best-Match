package com.bestmatch.BestMatch.repository;

import com.bestmatch.BestMatch.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    //select R.name, R.distance, R.price, R.rating, C.name  from restaurant R inner join cuisine C ON R.cuisine_id = C.id
}
