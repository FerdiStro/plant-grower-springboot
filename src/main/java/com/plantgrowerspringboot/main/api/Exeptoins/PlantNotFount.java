package com.plantgrowerspringboot.main.api.Exeptoins;

import com.plantgrowerspringboot.main.api.Exeptoins.PlantNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PlantNotFount {
    @ResponseBody
    @ExceptionHandler(PlantNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String employeeNotFoundHandler(PlantNotFoundException ex) {
        return ex.getMessage();
    }
}
