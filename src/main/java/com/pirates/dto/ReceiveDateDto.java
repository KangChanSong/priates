package com.pirates.dto;

import com.pirates.entity.Delivery;
import com.pirates.entity.DeliveryType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static java.time.DayOfWeek.*;

@AllArgsConstructor
@Data
public class ReceiveDateDto {
    private String date;
    public static List<ReceiveDateDto> toDtoList(Delivery delivery, LocalDateTime now){

        // 당일발송인지 익일발송인지 확인한다.
        if(delivery.getType() == DeliveryType.regular) now = now.plusDays(1);

        // 시간이 마감시간을 넘었는지 확인한다.
        String[] closing = delivery.getClosing().split(":");
        int closingHour = Integer.valueOf(closing[0]);
        int closingMinute = Integer.valueOf(closing[1]);

        if(now.getHour() > closingHour) now = now.plusDays(1);
        if(now.getHour() == closingHour && now.getMinute() > closingMinute) now.plusDays(1);

        //요일을 확인한다.
        DayOfWeek dayOfWeek = now.getDayOfWeek();
        if(dayOfWeek.equals(FRIDAY)) now = now.plusDays(2);
        if(dayOfWeek.equals(SATURDAY) || dayOfWeek.equals(SUNDAY)) now = now.plusDays(3);

        return convert(now);
    }

    private static List<ReceiveDateDto> convert(LocalDateTime now) {
        List<ReceiveDateDto> dtoList = new ArrayList<>();
        IntStream.range(0, 5).forEach(i ->
                dtoList.add(
                    new ReceiveDateDto(
                            toKorean(now.plusDays(i)))));

        return dtoList;

     }

    private static String toKorean(LocalDateTime time){
        return time.getMonthValue() + "월 " + time.getDayOfMonth() + "일 " + dayToKorean(time.getDayOfWeek());
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
            default: throw new IllegalStateException("요일은 평일이어야 합니다. 요일 : " + dayOfWeek);
        }
    }
}
