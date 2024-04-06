package com.proyecto.b.s.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class FormatterDateUtil {


    public static LocalDate formatterDate(String date) {

        String[] parts = date.split("/", 3);

        String dia = parts[0];
        String mes = parts[1];
        String ano = parts[2];


        dia = (dia.length() == 1) ? "0" + dia : dia;
        mes = (mes.length() == 1) ? "0" + mes : mes;
        ano = (ano.length() == 2) ? "20" + ano : ano;

        String date2 = dia + "/" + mes + "/" + ano;

        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("dd/MM/yyyy")
                .parseDefaulting(ChronoField.DAY_OF_MONTH, Long.parseLong(dia))
                .parseDefaulting(ChronoField.MONTH_OF_YEAR, Long.parseLong(mes))
                .parseDefaulting(ChronoField.YEAR, Long.parseLong(ano))
                .toFormatter();

        LocalDate localDate = LocalDate.parse(date2, formatter);

        return localDate;

    }


}
