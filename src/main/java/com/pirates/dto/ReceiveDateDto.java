package com.pirates.dto;

import com.pirates.entity.Delivery;
import com.pirates.entity.DeliveryType;
import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static java.time.DayOfWeek.*;

@Data
public class ReceiveDateDto {
    private String date;

    public ReceiveDateDto(LocalDateTime time){
        this.date = time.getMonthValue() + "월 " + time.getDayOfMonth() + "일 " + dayToKorean(time.getDayOfWeek());
    }

    public static List<ReceiveDateDto> toDtoList(Delivery delivery, LocalDateTime time){
        int addDate = 0;
        if(time.getDayOfWeek() == FRIDAY) addDate+=2;

        // 당일발송인지 익일발송인지 확인한다. 그리고 금요일인지 확인한다.
        if(delivery.getType() == DeliveryType.regular) addDate++;
        if(time.plusDays(addDate).getDayOfWeek() == FRIDAY) addDate+=2;

        // 시간이 마감시간을 넘었는지 확인한다. 그리고 금요일인지 확인한다.
        String[] closing = delivery.getClosing().split(":");
        int closingHour = Integer.valueOf(closing[0]);
        int closingMinute = Integer.valueOf(closing[1]);

        if(time.getHour() > closingHour) addDate++;
        if(time.getHour() == closingHour && time.getMinute() > closingMinute) addDate++;

        if(time.plusDays(addDate).getDayOfWeek() == FRIDAY) addDate+=2;

        return convert(time.plusDays(++addDate));
    }

    private static List<ReceiveDateDto> convert(LocalDateTime time) {
        List<ReceiveDateDto> dtoList = new ArrayList<>();
        IntStream.range(0, 5).forEach(i ->dtoList.add(new ReceiveDateDto(time.plusDays(i))));
        return dtoList;

     }

    private static String dayToKorean(DayOfWeek dayOfWeek){
        switch (dayOfWeek){
            case MONDAY: return "월요일";
            case TUESDAY: return "화요일";
            case WEDNESDAY: return "수요일";
            case THURSDAY: return "목요일";
            case FRIDAY: return "금요일";
            case SATURDAY: return "토요일";
            case SUNDAY: return "일요일";
        }

        return "";
    }
}
