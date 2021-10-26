package com.pirates.dto.product;

import com.pirates.dto.option.OptionRegisterDto;
import com.pirates.entity.DeliveryType;
import com.pirates.entity.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ProductRegisterDtoTest {

    @DisplayName("상품 등록 DTO 가 엔티티로 잘 변환된다.")
    @Test
    public void toEntityTest(){
        //given
        String name = "name";
        int optionSize = 100;
        String optionName = "option";
        String type = "fast";

        List<OptionRegisterDto> options = new ArrayList<>();
        IntStream.range(0, optionSize).forEach(i -> options.add(createOptionRegisterDto(optionName + i)));

        DeliveryRegisterDto deliveryDto = createDeliveryRegisterDto(type);

        ProductRegisterDto productDto = new ProductRegisterDto();
        productDto.setName("name");
        productDto.setDelivery(deliveryDto);
        productDto.setOptions(options);

        //when
        Product product = productDto.toEntity();

        //then
        assertEquals(product.getName(), name);
        assertEquals(product.getOptions().size() , 100);
        assertEquals(product.getDelivery().getType(), DeliveryType.fast);

    }

    private OptionRegisterDto createOptionRegisterDto(String name) {
        OptionRegisterDto dto = new OptionRegisterDto();
        dto.setName(name);
        dto.setPrice(1000);
        dto.setStock(123);
        return dto;
    }

    private DeliveryRegisterDto createDeliveryRegisterDto(String type){
        DeliveryRegisterDto dto = new DeliveryRegisterDto();
        dto.setClosing("12:00");
        dto.setType(type);
        dto.setPrice(10000);
        return dto;
    }

}