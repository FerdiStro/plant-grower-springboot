package com.plantgrowerspringboot.main;

import com.plant.plantgrow.model.*;
import com.plantgrowerspringboot.main.api.Exeptoins.PlantNotFoundException;
import com.plantgrowerspringboot.main.repository.Repository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Component
public class RequestHandler{

    private Repository repository;

    public RequestHandler(Repository repository){
        this.repository = repository;
    }


    public ResponseEntity<AllPlantDataResponse> getAllPlantData(){
        AllPlantDataResponse response  =  new AllPlantDataResponse();
        response.setAllPlant(repository.getAll());
        return new ResponseEntity<>(response, HttpStatus.OK );
    }

    public ResponseEntity<PlantDataNameResponse>  getPlantDataName(String name){
        PlantDataNameResponse response  = new PlantDataNameResponse() ;
        response.setPlant(checkIfExist(name, true));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<PutPumpNameResponse> setPumpName(String name, Integer pump){
        PutPumpNameResponse response = new PutPumpNameResponse();
        Plant plant = checkIfExist(name,true);
        plant.setPump(pump);
        repository.save(plant);
        response.setStatus(true);
        response.setPump(pump);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    public ResponseEntity<PumpNameResponse> getPumpName(String name) {
        Plant plant = checkIfExist(name,true);
        PumpNameResponse response  =  new PumpNameResponse();
        response.setPump(plant.getPump());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<SetPlantDataNameResponse> putDataName(String name, String data){
        SetPlantDataNameResponse response =  new SetPlantDataNameResponse();
        Plant plant = checkIfExist(name, false);
        if(plant != null){
            response.setNewPlant(false);
        }else{
            plant =  new Plant();
            plant.setName(name);
            plant.setPump(0);
            plant.setAvg(data);
            response.setNewPlant(true);
        }
        plant.setMos(data);
        plant.setLast(LocalDate.now().toString());

        repository.save(plant);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    public  ResponseEntity<DeleteNameResponse> deletePlantName(String name){
        DeleteNameResponse response  = new DeleteNameResponse();
        Plant plant = checkIfExist(name, true);
        repository.delete(plant);
        response.setOperation(true);
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
