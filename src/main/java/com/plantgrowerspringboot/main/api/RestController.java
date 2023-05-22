package com.plantgrowerspringboot.main.api;


import com.plantgrowerspringboot.main.plant.Plant;
import com.plantgrowerspringboot.main.repository.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;




@org.springframework.web.bind.annotation.RestController
public class RestController {

    Repository repository;

    public RestController(Repository repository){
        this.repository = repository;

    }

    @PostMapping(value = "/data/{name}")
    public void putData(@PathVariable("name") String name){
        Plant plant = new Plant();
        plant.setName(name);
        plant.setMos(0);

        repository.save(plant);
    }
}
