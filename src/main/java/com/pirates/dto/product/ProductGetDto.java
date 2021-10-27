package com.pirates.dto.product;

import com.pirates.entity.Product;
import lombok.Data;

@Data
public class ProductGetDto {
    private String name;
    private String description;
    private String price;

    public static ProductGetDto toDto(Product product, Long lowestPrice){
        ProductGetDto dto = new ProductGetDto();
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(convertToString(lowestPrice));
        return dto;
    }

    private static String convertToString(Long price){
        StringBuffer buffer = new StringBuffer();
        buffer.append(price);
        buffer.reverse();

        char[] chars = buffer.toString().toCharArray();
        buffer.delete(0, chars.length);

        for(int i = 1 ; i <= chars.length ; i++){
            if(i > 1 && (i-1) % 3 == 0){
                buffer.append(",");
            }
            buffer.append(chars[i-1]);
        }

        return buffer.reverse().append(" ~").toString();
    }
}
