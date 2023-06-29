package com.plantgrowerspringboot.main.api;


import com.plantgrowerspringboot.main.api.Exeptoins.PlantNotFoundException;
import com.plantgrowerspringboot.main.api.Response.GetAllResponse;
import com.plantgrowerspringboot.main.api.Response.PutDataForNameResponse;
import com.plantgrowerspringboot.main.api.Response.PutPumpForNameResponse;
import com.plantgrowerspringboot.main.plant.Plant;
import com.plantgrowerspringboot.main.repository.Repository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@org.springframework.web.bind.annotation.RestController
public class RestController {


    Repository repository;

    public RestController(Repository repository){
        this.repository = repository;


    }

    @CrossOrigin
    @GetMapping(value = "/data/{name}")
    public ResponseEntity<PutDataForNameResponse> putData(@RequestParam(value="mos")String mos, @PathVariable("name") String name){
        Plant plant = repository.get(name);
        if(plant != null) {
            plant.setMos(mos);
            plant.setLast(LocalDate.now());
        }else {
            plant =  new Plant();
            plant.setPump(0);
            plant.setName(name);
            plant.setMos(mos);
            plant.setLast(LocalDate.now());
            plant.setStatus(true);
            plant.setAvg("50.00");
        }
        repository.save(plant);
        PutDataForNameResponse putDataResponse = new PutDataForNameResponse();
        putDataResponse.setPump(plant.getPump());
        return new ResponseEntity<>(putDataResponse, HttpStatus.OK);
    }

    @CrossOrigin
    @PutMapping(value = "/pump/{name}")
    public ResponseEntity<PutPumpForNameResponse> putPump(@RequestParam(value="pumpState")int pumpState, @PathVariable("name") String name){
        System.out.println("TESTETTETTE");
        Plant plant = repository.get(name);
        if(plant != null){
            plant.setPump(pumpState);
            repository.save(plant);
            PutPumpForNameResponse response =  new PutPumpForNameResponse();
            response.setName(name);
            response.setSetPump(pumpState);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else{
            throw new PlantNotFoundException(name);
        }
    }
    @CrossOrigin
    @GetMapping(value = "/getAll")
    public ResponseEntity<GetAllResponse> getAll(){
        GetAllResponse response  =  new GetAllResponse();
        response.setAllPlant(repository.getAll());
        System.out.println("ALLL");
        return new ResponseEntity<>(response,  HttpStatus.OK);
    }

}
