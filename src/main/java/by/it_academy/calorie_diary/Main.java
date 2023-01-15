package by.it_academy.calorie_diary;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

public class Main {
    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.of(2023, Month.JANUARY, 12, 22, 40, 36, 554000000);
        System.out.println(localDateTime);

        ZonedDateTime zdt = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
        Instant instant = zdt.toInstant();
        long date = instant.toEpochMilli();
        System.out.println(localDateTime);
        System.out.println(instant);
        System.out.println(date);

       // 2022-12-22 16:11:30.262
//        LocalDateTime updateDate = LocalDateTime.ofInstant(
//                Instant.ofEpochMilli(date),
//                ZoneId.of("UTC")
//        );

        LocalDateTime updateDate = LocalDateTime.ofInstant(
                Instant.ofEpochMilli(date),
                TimeZone.getDefault().toZoneId());

       // DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSSSSS");


//        LocalDateTime time = LocalDateTime.parse(updateDate, formatter));

        System.out.println(updateDate);
//        System.out.println(time);


    }
}
