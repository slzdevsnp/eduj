package org.slzdev.csv;


import com.univocity.parsers.annotations.Convert;
import com.univocity.parsers.annotations.Headers;
import com.univocity.parsers.annotations.Parsed;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Headers(sequence = {"Id","ForecastDate","ValueDate","Value"}, extract = true)
public class PointConnectDataPoint {

    @Parsed
    private Integer Id;

    @Parsed
    @Convert(conversionClass=LocalDateTimeConverter.class, args="dd.MM.yyyy HH:mm:ss")
    private LocalDateTime ForecastDate;

    @Parsed
    @Convert(conversionClass=LocalDateTimeConverter.class, args="dd.MM.yyyy HH:mm:ss")
    private LocalDateTime ValueDate ;

    @Parsed
    private Double Value;
}
