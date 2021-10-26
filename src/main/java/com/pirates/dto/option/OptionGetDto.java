package com.pirates.dto.option;

import com.pirates.entity.Option;
import lombok.Data;

@Data
public class OptionGetDto {
    private String name;
    private int price;

    public static OptionGetDto toDto(Option option){
        OptionGetDto dto = new OptionGetDto();
        dto.setName(option.getName());
        dto.setPrice(option.getPrice());
        return dto;
    }
}
