package com.pirates.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Product extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;
    private String name;
    private String description;

    @Embedded
    private Delivery delivery;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "product")
    private List<Option> options = new ArrayList<>();

    @Builder
    public Product(String name, String description, Delivery delivery, List<Option> options){
        this.name = name;
        this.description = description;
        this.delivery = delivery;
        options.forEach(option -> option.setProduct(this));
        this.options = options;
    }
}