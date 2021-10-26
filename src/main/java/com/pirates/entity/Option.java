package com.pirates.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Option extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "option_id")
    private Long id;

    private String name;
    private int price;
    private int stock;

    @Builder
    public Option(String name, int price, int stock){
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
}
