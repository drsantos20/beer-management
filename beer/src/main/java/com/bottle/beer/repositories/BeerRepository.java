package com.bottle.beer.repositories;

import com.bottle.beer.entity.Beer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author daniel.santos
 */
@Repository
public interface BeerRepository extends JpaRepository<Beer, Long> {

    @Query("SELECT be FROM Beer be WHERE :temperature BETWEEN be.min AND be.max")
    List<Beer> findByMinOrMax(@Param("temperature") Long temperature);
}
