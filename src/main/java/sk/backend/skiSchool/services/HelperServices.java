package sk.backend.skiSchool.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HelperServices {

    public static String currentDay() {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String formattedDay = date.format(formatter);

        return formattedDay;
        
        
    }
    
}
