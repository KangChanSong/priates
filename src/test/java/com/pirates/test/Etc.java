package com.pirates.test;

import com.pirates.entity.DeliveryType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Etc {

    @DisplayName("Enum .name() 메서드 테스트")
    @Test
    public void ENUM_TEST(){
        assertEquals(DeliveryType.fast.name(), "fast");
    }

    @DisplayName("LocalDateTime 활용 테스트")
    @Test
    public void LocalDateTimeTest(){
        LocalDateTime time = LocalDateTime.now();
        System.out.println("time.getDayOfMonth() = " + time.getDayOfMonth());
        System.out.println("time.getHour() = " + time.getHour());
        System.out.println("time.getMinute() = " + time.getMinute());
        System.out.println("time.getDayOfWeek() = " + time.getDayOfWeek());
        System.out.println("time.getMinute() = " + time.getMinute());

    }
}
