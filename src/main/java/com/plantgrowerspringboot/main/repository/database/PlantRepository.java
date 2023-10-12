package com.plantgrowerspringboot.main.repository.database;

import com.plant.plantgrow.model.Plant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.toIntExact;


public interface PlantRepository extends JpaRepository<PlantEntity, Long> {

    default  Plant converToPlant(PlantEntity plantEntity){
        Plant plant =  new Plant();
        plant.setPump(plantEntity.getPump());
        plant.setLast(plantEntity.getLast());
        plant.setAvg(plantEntity.getAvg());
        plant.setName(plantEntity.getName());
        plant.setMos(plantEntity.getMos());
        plant.setPb(plantEntity.getPb());
        plant.setId(toIntExact(plantEntity.getId()));
        return plant;
    }

    default PlantEntity convertToPlantEntity(Plant plant){
        PlantEntity plantEntity =  new PlantEntity();
        plantEntity.setPump(plant.getPump());
        plantEntity.setLast(plant.getLast());
        plantEntity.setAvg(plant.getAvg());
        plantEntity.setName(plant.getName());
        plantEntity.setMos(plant.getMos());
        plantEntity.setPb(plant.getPb());
        if(plant.getId() != null){
            plantEntity.setId(Long.valueOf(plant.getId()));
        }
        return plantEntity;
    }

    default List<Plant> converToPlantList(List<PlantEntity> plantsE){
        List<Plant> plants =  new ArrayList<>();
        for(PlantEntity plantE: plantsE){
            plants.add(converToPlant(plantE));
        }
        return plants;
    }
}
