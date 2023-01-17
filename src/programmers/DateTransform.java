package programmers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class DateTransform {
    public static List<String> transformDateFormat(List<String> dates) {
        List<String> result = new ArrayList<String>();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        for(String date : dates) {
            LocalDate lDate = changeFormat(date);
            if ( lDate != null ) {
                result.add(dateTimeFormatter.format(lDate));
            }
        }
        return result;
    }

    public static LocalDate changeFormat(String date) {
        LocalDate localDate = null;
        try {
            if (date.contains("/")) {
                if (date.indexOf("/") > 2) {
                    localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
                } else {
                    localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                }
            } else if (date.contains("-")) {
                localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("MM-dd-yyyy"));
            } else {
                return null;
            }
            return localDate;
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public static void main(String[] args) {
        List<String> dates = transformDateFormat(Arrays.asList("2015/02/20", "19/12/2016", "11-18-2012", "20130720"));
        for(String date : dates) {
            System.out.println(date);
        }
    }
}
