package com.plantgrowerspringboot.main.api;


import com.plant.plantgrow.api.PlantGrowApi;
import com.plant.plantgrow.model.*;
import com.plantgrowerspringboot.main.RequestHandler;
import org.springframework.http.ResponseEntity;


@org.springframework.web.bind.annotation.RestController
public class RestController implements PlantGrowApi {



    RequestHandler requestHandler;

    public RestController(RequestHandler requestHandler){
        this.requestHandler = requestHandler;
    }

    @Override
    public ResponseEntity<AllPlantDataResponse> allPlantData() {
       return requestHandler.getAllPlantData();
    }

    @Override
    public ResponseEntity<DeleteNameResponse> deletePlantName(String name) {
        return requestHandler.deletePlantName(name);
    }


    @Override
    public ResponseEntity<PumpNameResponse> getPumpName(String name) {
        return requestHandler.getPumpName(name);
    }

    @Override
    public ResponseEntity<SetPlantDataNameResponse> putDataName(String name, String mos) {
        return requestHandler.putDataName(name, mos);
    }

    @Override
    public ResponseEntity<PutPumpNameResponse> setPumpName(String name, Integer pump) {
        return requestHandler.setPumpName(name, pump);
    }

    @Override
    public ResponseEntity<PlantDataNameResponse> getDataName(String name) {
        return requestHandler.getPlantDataName(name);
    }




}
