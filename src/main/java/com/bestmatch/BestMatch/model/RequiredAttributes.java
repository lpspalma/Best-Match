package com.bestmatch.BestMatch.model;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class RequiredAttributes {
    private String name;
    private int rating;
    private int distance;
    private int price;
    @Override
    public String toString() {
        return
                "name='" + name + '\'' +
                ", rating=" + rating +
                ", distance=" + distance +
                ", price=" + price;
    }
}
