package com.plantgrowerspringboot.main.repository;

import com.plantgrowerspringboot.main.plant.Plant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Repository {


    public void save(Plant plant){
        log.info("Name: "+ plant.getName() + "| MOS: " +  plant.getMos() );
    }



}
