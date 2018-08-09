package com.bottle.beer.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author daniel.santos
 */
@Entity
public class Beer {
    @Id
    @GeneratedValue
    private Long id;
    private String beerStyle;
    private Long min;
    private Long max;

    public Beer(String beerStyle, Long min, Long max) {
        this.beerStyle = beerStyle;
        this.min = min;
        this.max = max;
    }

    public Beer() {
    }

    public String getBeerStyle() {
        return beerStyle;
    }

    public Long getMin() {
        return min;
    }

    public Long getMax() {
        return max;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
