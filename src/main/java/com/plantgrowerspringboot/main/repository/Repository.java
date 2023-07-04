package com.plantgrowerspringboot.main.repository;


import com.plant.plantgrow.model.Plant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class Repository {



    HashMap<String, Plant> plantHashMap =  new HashMap<>();




    public void save(Plant plant){
        plantHashMap.put(plant.getName(), plant);
    }
    public Plant get(String name){
      return   plantHashMap.get(name);
    }

    public List<Plant> getAll(){
        List<Plant> list = new ArrayList<>();
        for(Map.Entry<String, Plant> entry : plantHashMap.entrySet()) {
            list.add(entry.getValue());
        }
        return list;
    }



}
