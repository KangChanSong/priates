package com.pirates.dto.product;

import com.pirates.entity.Delivery;
import com.pirates.entity.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductGetDtoTest {

    @DisplayName("상품이 상품 단순 조회 DTO 로 잘 변환된다")
    @Test
    public void toDtoTest(){
        //given
        String name = "상품";
        int price = 1030000;
        Product product = Product.builder()
                .name(name)
                .delivery(Delivery.builder().price(price).build())
                .build();

        //when
        ProductGetDto productGetDto = ProductGetDto.toDto(product);
        //then
        assertEquals(productGetDto.getName(), name);
        assertEquals(productGetDto.getPrice(), "1,030,000 ~");
    }
}