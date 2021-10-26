package com.pirates.dto.product;

import com.pirates.dto.option.OptionRegisterDto;
import com.pirates.entity.Product;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ProductRegisterDto {
    private String name;
    private String description;
    private DeliveryRegisterDto delivery;
    private List<OptionRegisterDto> options;

    public Product toEntity(){
        return Product.builder()
                .name(name)
                .description(description)
                .delivery(delivery.toEmbeddable())
                .options(options.stream()
                        .map(option -> option.toEntity())
                        .collect(Collectors.toList()))
                .build();
    }
}
