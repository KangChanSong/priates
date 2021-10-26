package com.pirates.dto.product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeliveryDtoTest {

    @DisplayName("type 필드에 fast와 regular만 할당할 수 있다.")
    @Test
    public void typeExceptionTest(){
        //when
        DeliveryRegisterDto dto = new DeliveryRegisterDto();
        dto.setType("asd");

        //then
        assertThrows(IllegalStateException.class, () -> {
            dto.toEmbeddable();
        });
    }
}
