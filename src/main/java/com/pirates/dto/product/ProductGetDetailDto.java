package com.pirates.dto.product;

import com.pirates.dto.option.OptionGetDto;
import com.pirates.entity.Product;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ProductGetDetailDto {
    private String name;
    private String description;
    private String delivery;
    private List<OptionGetDto> options;

    public static ProductGetDetailDto toDto(Product product){

        ProductGetDetailDto dto = new ProductGetDetailDto();
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setDelivery(product.getDelivery().getType().name());
        dto.setOptions(product.getOptions().stream()
                .map(option -> OptionGetDto.toDto(option))
                .collect(Collectors.toList()));

        return dto;
    }
}
