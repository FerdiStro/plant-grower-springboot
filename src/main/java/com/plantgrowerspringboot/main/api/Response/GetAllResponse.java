package com.plantgrowerspringboot.main.api.Response;

import com.plantgrowerspringboot.main.plant.Plant;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class GetAllResponse {
    List<Plant> allPlant;
}
