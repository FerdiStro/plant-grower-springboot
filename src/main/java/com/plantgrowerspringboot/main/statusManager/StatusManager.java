package com.plantgrowerspringboot.main.statusManager;


import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;


@Slf4j
@Component
public class StatusManager {

    HashMap<String, Boolean> statusMap =  new HashMap<>();






    @Scheduled(fixedRate = 20000)
    private void setStatusFalse(){
        statusMap.replaceAll((k, v) -> false);
    }

    public void setStatusTrue(String name){
        statusMap.put(name, true);
    }

    public Boolean getStatus(String name){
        return statusMap.get(name);
    }

}
