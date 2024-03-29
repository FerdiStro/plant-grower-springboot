package com.plantgrowerspringboot.main.api.exeptoins;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class PlantNotFount {
    @ResponseBody
    @ExceptionHandler(PlantNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String plantNotFoundHandler(PlantNotFoundException ex) {
        log.error(ex.getMessage());
        return ex.getMessage();
    }
}
