package com.plantgrowerspringboot.main.api.Response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PutPumpForNameResponse {
    public Integer setPump;
    public String name;
    public String errorMassage;
}
