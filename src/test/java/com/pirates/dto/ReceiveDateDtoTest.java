package com.pirates.dto;

import com.pirates.entity.Delivery;
import com.pirates.entity.DeliveryType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static com.pirates.entity.DeliveryType.fast;
import static com.pirates.entity.DeliveryType.regular;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ReceiveDateDtoTest {

    @DisplayName("금요일 마감 전 익일 발송으로 주문시 화요일부터 수령이 가능하다..")
    @Test
    public void FRIDAY_REGULAR_OPEN(){
        //given
        Delivery delivery = getDeliveryClosing12AM(regular);
        //when
        List<ReceiveDateDto> dtoList = ReceiveDateDto.toDtoList(delivery, getFriday( 11, 59));
        //then
        assertTrue(dtoList.get(0).getDate().contains("화요일"));
    }
    @DisplayName("금요일 마감 후 익일 발송 시 수요일부터 수령이 가능하다")
    @Test
    public void FRIDAY_REGULAR_CLOSED(){
        //given
        Delivery delivery = getDeliveryClosing12AM(regular);
        //when
        List<ReceiveDateDto> dtoList = ReceiveDateDto.toDtoList(delivery, getFriday( 12, 1));
        //then
        System.out.println("dtoList = " + dtoList.get(0).getDate());
        assertTrue(dtoList.get(0).getDate().contains("수요일"));
    }

    @DisplayName("금요일 마감 전 당일 발송 시 월요일부터 수령이 가능하다.")
    @Test
    public void FRIDAY_FAST_OPEN(){
        //given
        Delivery delivery = getDeliveryClosing12AM(fast);
        //when
        List<ReceiveDateDto> dtoList = ReceiveDateDto.toDtoList(delivery, getFriday(11, 59));
        //then
        System.out.println("dtoList.get(9) = " +dtoList.get(0).getDate());
        assertTrue(dtoList.get(0).getDate().contains("월요일"));
    }

    @DisplayName("금요일 마감 후 당일 발송으로 주문 시 화요일부터 수령이 가능하다.")
    @Test
    public void FRIDAY_FAST_CLOSED(){
        //given
        Delivery delivery = getDeliveryClosing12AM(fast);
        //when
        List<ReceiveDateDto> dtoList = ReceiveDateDto.toDtoList(delivery, getFriday( 12, 1));
        //then
        assertTrue(dtoList.get(0).getDate().contains("화요일"));
    }

    @DisplayName("목요일 마감 후 익일 발송 시 화요일부터 수령이 가능하다")
    @Test
    public void THURSDAY_REGULAR_CLOSED(){
        //given
        LocalDateTime orderTime = getThursday(12, 1);
        Delivery delivery = getDeliveryClosing12AM(regular);

        //when
        List<ReceiveDateDto> dtoList = ReceiveDateDto.toDtoList(delivery, orderTime);

        //then
        assertTrue(dtoList.get(0).getDate().contains("화요일"));
    }

    @DisplayName("목요일 마감 전 익일 발송 시 월요일부터 수령이 가능하다")
    @Test
    public void THURSDAY_REGULAR_OPEN(){
        //given
        LocalDateTime orderTime = getThursday(11, 59);
        Delivery delivery = getDeliveryClosing12AM(regular);

        //when
        List<ReceiveDateDto> dtoList = ReceiveDateDto.toDtoList(delivery, orderTime);

        //then
        assertTrue(dtoList.get(0).getDate().contains("월요일"));
    }

    @DisplayName("목요일 마감 후 당일 발송 시 월요일부터 수령이 가능하다")
    @Test
    public void THURSDAY_FAST_CLOSED(){
        //given
        LocalDateTime orderTime = getThursday(12, 1);
        Delivery delivery = getDeliveryClosing12AM(fast);

        //when
        List<ReceiveDateDto> dtoList = ReceiveDateDto.toDtoList(delivery, orderTime);

        //then
        assertTrue(dtoList.get(0).getDate().contains("월요일"));
    }

    @DisplayName("목요일 마감 전 당일 발송 시 금요일부터 수령이 가능하다")
    @Test
    public void THURSDAY_FAST_OPEN(){
        //given
        LocalDateTime orderTime = getThursday(11, 59);
        Delivery delivery = getDeliveryClosing12AM(fast);

        //when
        List<ReceiveDateDto> dtoList = ReceiveDateDto.toDtoList(delivery, orderTime);

        //then
        assertTrue(dtoList.get(0).getDate().contains("금요일"));
    }
    private LocalDateTime getFriday(int hour, int minute){
        return LocalDateTime.of(2021, 10, 29, hour, minute);
    }

    private LocalDateTime getThursday(int hour, int minute){
        return LocalDateTime.of(2021,10,28, hour, minute);
    }

    private Delivery getDeliveryClosing12AM(DeliveryType type){
        return Delivery.builder()
                .type(type)
                .closing("12:00")
                .build();
    }


}