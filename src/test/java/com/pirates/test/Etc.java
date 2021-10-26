package com.pirates.test;

import com.pirates.entity.DeliveryType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Etc {

    @DisplayName("Enum .name() 메서드 테스트")
    @Test
    public void ENUM_TEST(){
        assertEquals(DeliveryType.fast.name(), "fast");
    }
}
