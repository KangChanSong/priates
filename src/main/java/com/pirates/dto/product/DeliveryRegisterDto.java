package com.pirates.dto.product;

import com.pirates.entity.Delivery;
import com.pirates.entity.DeliveryType;
import lombok.Data;

@Data
public class DeliveryRegisterDto {
    private String type;
    private String closing;
    private int price;

    public Delivery toEmbeddable(){
        return Delivery.builder()
                .type(toEnum(type))
                .closing(closing)
                .price(price)
                .build();
    }

    private DeliveryType toEnum(String type){
        if(!type.equals("fast") && !type.equals("regular")){
            throw new IllegalStateException("type 은 fast 나 regular 이어야 합니다. type : " + type );
        }
        return DeliveryType.valueOf(type);
    }
}
