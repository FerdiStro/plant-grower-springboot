package com.plantgrowerspringboot.main.repository;


import com.plant.plantgrow.model.Plant;
import com.plantgrowerspringboot.main.repository.database.PlantRepository;
import com.plantgrowerspringboot.main.repository.database.PlantEntity;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class Repository {

    private Map<String, Long> idMap = new HashMap<>();
    private final PlantRepository plantRepository;

    public Repository(PlantRepository plantRepository){
        this.plantRepository =  plantRepository;
    }


    public void save(Plant plant){
        if(idMap.get(plant.getName()) !=null){
            plant.setId(Math.toIntExact(idMap.get(plant.getName())));
        }
        plantRepository.save(plantRepository.convertToPlantEntity(plant));
        refresh();
    }
    public Plant get(String name){
        if(idMap.get(name)==null){
            return null;
        }
        return plantRepository.converToPlant(plantRepository.getReferenceById(idMap.get(name)));
    }

    public List<Plant> getAll(){
        return plantRepository.converToPlantList(plantRepository.findAll());
    }

    public void delete(Plant plant){
        plantRepository.delete(plantRepository.convertToPlantEntity(plant));
        refresh();
    }

    @PostConstruct
    public void refresh(){
        for(PlantEntity plant: plantRepository.findAll()){
            idMap.put(plant.getName(), plant.getId());
        }
    }



}
