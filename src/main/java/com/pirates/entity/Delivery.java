package com.pirates.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@NoArgsConstructor
@Getter
@Embeddable
public class Delivery {

    @Enumerated(EnumType.STRING)
    private DeliveryType type;
    private String closing;
    private int price;

    @Builder
    public Delivery(DeliveryType type, String closing, int price){
        this.type = type;
        this.closing = closing;
        this.price = price;
    }
}
