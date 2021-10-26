package com.pirates.dto.option;

import com.pirates.entity.Option;
import lombok.Data;

@Data
public class OptionRegisterDto {
    private String name;
    private int price;
    private int stock;

    public Option toEntity(){
        return Option.builder()
                .name(name)
                .price(price)
                .stock(stock)
                .build();
    }
}
