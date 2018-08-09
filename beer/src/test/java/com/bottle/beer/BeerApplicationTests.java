package com.bottle.beer;

import com.bottle.beer.entity.Beer;
import com.bottle.beer.repositories.BeerRepository;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BeerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BeerApplicationTests {

    @Autowired
    private BeerRepository beerRepository;

    @Test
    public void test_save_beer() throws Exception {
        beerRepository.save(new Beer("Brahma", -1L, 3L));
        Assert.assertNotNull(beerRepository.findByMinOrMax(2L));

    }

    @Test
    public void test_beer_ordering_by_letter() throws Exception {
        beerRepository.save(new Beer("Heineken", -1L, 3L));
        beerRepository.save(new Beer("Brahma", -1L, 3L));


        List<Beer> beers = beerRepository.findByMinOrMax(2L);

        beers = beers.stream().sorted(Comparator.comparing(Beer::getBeerStyle)).collect(Collectors.toList());

        beers.forEach(item->System.out.println(item.getBeerStyle()));




    }



}
