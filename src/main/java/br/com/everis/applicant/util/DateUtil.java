package br.com.everis.applicant.util;

import br.com.everis.applicant.exceptions.NotImplementationConstructionException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {

    private DateUtil(){
        throw new NotImplementationConstructionException("Classe não pode ser instanciada");
    }

    public static LocalDateTime convert(Date date) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        ZonedDateTime zdt = cal.toZonedDateTime();
        return zdt.toLocalDateTime();
    }

    public static Date convert(LocalDateTime ldt) {
        ZonedDateTime zdt = ZonedDateTime.of(ldt, ZoneId.systemDefault());
        GregorianCalendar cal = GregorianCalendar.from(zdt);
        return cal.getTime();
    }

    public static String convertBRTime(LocalDateTime ldt) {
        var brFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return ldt.format(brFormatter);
    }

    public static Date convert(String date) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        try {
            return formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String convertDate(Date date) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        return formatter.format(date);
    }
}
