package com.bestmatch.BestMatch.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Restaurant extends RequiredAttributes {
    @Builder
    public Restaurant(String name, int rating, int distance, int price, Cuisine cuisine) {
        super(name, rating, distance, price);
        this.cuisine = cuisine;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Cuisine_id")
    private Cuisine cuisine;

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id + '\'' +
                ", " +
                super.toString() +
                ", cuisine=" + cuisine.getName() +
                '}';
    }
}
