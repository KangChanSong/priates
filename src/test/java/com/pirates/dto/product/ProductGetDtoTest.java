package com.pirates.dto.product;

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
        Product product = Product.builder()
                .name(name)
                .build();

        //when
        ProductGetDto productGetDto = ProductGetDto.toDto(product, 1030000);
        //then
        assertEquals(productGetDto.getName(), name);
        assertEquals(productGetDto.getPrice(), "1,030,000 ~");
    }
}