package com.plantgrowerspringboot.main.api;


import com.plant.plantgrow.api.PlantGrowApi;
import com.plant.plantgrow.api.PublicStatsApi;
import com.plant.plantgrow.model.*;
import com.plantgrowerspringboot.main.PlantRequestHandler;
import com.plantgrowerspringboot.main.PublicStatsRequestHandler;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;


@org.springframework.web.bind.annotation.RestController
public class RestController implements PlantGrowApi, PublicStatsApi {



    private  final PlantRequestHandler plantRequestHandler;
    private final PublicStatsRequestHandler publicStatsRequestHandler;

    public RestController(
            PlantRequestHandler       plantRequestHandler      ,
            PublicStatsRequestHandler publicStatsRequestHandler
    ){
        this.plantRequestHandler       = plantRequestHandler      ;
        this.publicStatsRequestHandler = publicStatsRequestHandler;
    }

    @Override
    public ResponseEntity<AllPlantDataResponse> allPlantData() {
       return plantRequestHandler.getAllPlantData();
    }

    @Override
    public ResponseEntity<DeleteNameResponse> deletePlantName(String name) {
        return plantRequestHandler.deletePlantName(name);
    }

    @Override
    public ResponseEntity<PumpNameResponse> getPumpName(String name) {
        return plantRequestHandler.getPumpName(name);
    }

    @Override
    public ResponseEntity<SetPlantDataNameResponse> putDataName(String name, String mos, SetDataBody setDataBody) {
        return plantRequestHandler.putDataName(name, mos, setDataBody.getPb());
    }

    @Override
    public ResponseEntity<PutPumpNameResponse> setPumpName(String name, Integer pump) {
        return plantRequestHandler.setPumpName(name, pump);
    }

    @Override
    public ResponseEntity<PlantDataNameResponse> getDataName(String name) {
        return plantRequestHandler.getPlantDataName(name);
    }

    @Override
    @CrossOrigin()
    public ResponseEntity<Resource> getStatsImageName(String name, String contentType) {
        return publicStatsRequestHandler.getStatsImageByName(name, contentType);
    }
}
