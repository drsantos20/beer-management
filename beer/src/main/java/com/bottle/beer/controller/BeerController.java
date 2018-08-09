package com.bottle.beer.controller;

import com.bottle.beer.entity.Beer;
import com.bottle.beer.exception.BeerNotFoundException;
import com.bottle.beer.repositories.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;


/**
 * @author daniel.santos
 */
@RestController
public class BeerController {

    @Autowired
    private BeerRepository beerRepository;

    @GetMapping("/beer")
    public List<Beer> retrieveAllStudents() {
        return beerRepository.findAll();
    }

    @GetMapping("/beer/{id}")
    public List<Beer> retrieveStudent(@PathVariable long id) {
        List<Beer> beer = beerRepository.findByMinOrMax(id);

        if (beer.isEmpty())
            throw new BeerNotFoundException("temperature-" + id);

        return beer;
    }

    @DeleteMapping("/beer/{id}")
    public void deleteStudent(@PathVariable long id) {
        beerRepository.deleteById(id);
    }

    @PostMapping("/beer")
    public ResponseEntity<Object> createStudent(@RequestBody Beer beer) {
        Beer savedBeer = beerRepository.save(beer);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedBeer.getId()).toUri();

        return ResponseEntity.created(location).build();

    }

    @PutMapping("/beer/{id}")
    public ResponseEntity<Object> updateStudent(@RequestBody Beer beer, @PathVariable long id) {

        Optional<Beer> studentOptional = beerRepository.findById(id);

        if (!studentOptional.isPresent())
            return ResponseEntity.notFound().build();


        beerRepository.save(beer);

        return ResponseEntity.noContent().build();
    }
}
