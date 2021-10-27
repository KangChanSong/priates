package com.pirates.dto;

import com.pirates.entity.Delivery;
import com.pirates.entity.DeliveryType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

class ReceiveDateDtoTest {

    @DisplayName("금요일 마감 이후 익일 발송으로 주문시 수령일을 화 수 목 금 토 로 출력한다..")
    @Test
    public void ORDER_TEST(){
        //given
        LocalDateTime orderTime = LocalDateTime.of(2021,10,29, 12, 30);

        Delivery delivery = Delivery.builder()
                .type(DeliveryType.regular)
                .closing("12:00")
                .build();

        //when
        List<ReceiveDateDto> dtoList = ReceiveDateDto.toDtoList(delivery, orderTime);

        //then
        System.out.println("dtoList = " + dtoList.toString());
    }


}