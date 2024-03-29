package com.plantgrowerspringboot.main;

import com.plant.plantgrow.model.*;
import com.plantgrowerspringboot.main.api.exeptoins.PlantNotFoundException;
import com.plantgrowerspringboot.main.repository.Repository;
import com.plantgrowerspringboot.main.statusManager.StatusManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class PlantRequestHandler {

    private Repository repository;
    private StatusManager statusManager;


    public PlantRequestHandler(Repository repository, StatusManager statusManager){
        this.repository = repository;
        this.statusManager =  statusManager;

    }


    public ResponseEntity<AllPlantDataResponse> getAllPlantData(){
        AllPlantDataResponse response  =  new AllPlantDataResponse();
        List<Plant> all = repository.getAll();
        List<PlantAll> allPlantsList  =  new ArrayList<>();
        for(Plant a: all){
            PlantAll plantAll  = new PlantAll();
            plantAll.setPlant(a);
            allPlantsList.add(plantAll);
            plantAll.setStatus(statusManager.getStatus(a.getName()));
        }
        response.setAllPlant(allPlantsList);
        return new ResponseEntity<>(response, HttpStatus.OK );
    }

    public ResponseEntity<PlantDataNameResponse>  getPlantDataName(String name){
        PlantDataNameResponse response  = new PlantDataNameResponse() ;
        response.setPlant(checkIfExist(name, true));
        response.setStatus(statusManager.getStatus(name));
        log.info(response.toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<PutPumpNameResponse> setPumpName(String name, Integer pump){
        PutPumpNameResponse response = new PutPumpNameResponse();
        Plant plant = checkIfExist(name,true);
        plant.setPump(pump);
        repository.save(plant);
        response.setPump(pump);
        log.info(response.toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    public ResponseEntity<PumpNameResponse> getPumpName(String name) {
        Plant plant = checkIfExist(name,true);
        PumpNameResponse response  =  new PumpNameResponse();
        response.setPump(plant.getPump());
        log.info(response.toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<SetPlantDataNameResponse> putDataName(String name, String data, String pb){
        SetPlantDataNameResponse response =  new SetPlantDataNameResponse();
        Plant plant = checkIfExist(name, false);
        if(plant != null){
            response.setNewPlant(false);
        }else{
            plant =  new Plant();
            plant.setName(name);
            plant.setPump(0);
            plant.setPb(pb);
            plant.setAvg(data);
            response.setNewPlant(true);
        }
        plant.setMos(data);
        plant.setLast(LocalDate.now().toString());
        statusManager.setStatusTrue(name);
        repository.save(plant);
        log.info(response.toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    public  ResponseEntity<DeleteNameResponse> deletePlantName(String name){
        DeleteNameResponse response  = new DeleteNameResponse();
        Plant plant = checkIfExist(name, true);
        repository.delete(plant);
        response.setOperation(true);
        log.info(response.toString());
        return new ResponseEntity<>( response , HttpStatus.OK);
    }


    private Plant checkIfExist(String name, boolean ex){
        Plant plant = repository.get(name);
        if(plant != null){
            return plant;
        }else if (ex) {
            throw new PlantNotFoundException(name);
        }
        return null;
    }










}
