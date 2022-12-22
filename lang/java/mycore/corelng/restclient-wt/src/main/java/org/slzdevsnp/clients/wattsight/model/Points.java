package org.slzdevsnp.clients.wattsight.model;

import lombok.*;

import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString
public class Points {
    private List<Point> points;

    private Points(List<Point> points) {
        this.points = points;
    }

    public static Points from(List<Double[]> rawPoints) {
        List<Point> r = new ArrayList<>();

        for (Double[] rawPoint : rawPoints) {
            r.add(
                    Point.builder()
                            .timestampUtc(
                                    Instant.ofEpochMilli(rawPoint[0].longValue())
                                            .atZone(ZoneId.of("UTC"))
                                            .toLocalDateTime()
                            )
                            .value(rawPoint[1])
                            .build()
            );
        }
        return new Points(r);
    }
}
