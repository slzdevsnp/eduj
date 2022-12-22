package org.slzdev.csv;

import com.univocity.parsers.conversions.Conversion;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeConverter  implements Conversion<String, LocalDateTime> {

    private  String format = "yyyy-MM-dd'T'HH:mm:ss.SSS";

    public LocalDateTimeConverter(String... args) {
        if(args.length > 0){
            format = args[0];
        }
    }

    @Override
    public LocalDateTime execute(String s) {
        if(s == null){
            return null;
        }
        return LocalDateTime.parse(s, DateTimeFormatter.ofPattern(format));
    }

    @Override
    public String revert(LocalDateTime o) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return o.format(formatter);
    }
}
