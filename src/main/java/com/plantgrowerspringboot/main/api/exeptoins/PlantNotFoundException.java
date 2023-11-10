package com.plantgrowerspringboot.main.api.exeptoins;

public class PlantNotFoundException extends  RuntimeException{
    public PlantNotFoundException(String id){
        super("Could not find Plant " + id);
    }
}
