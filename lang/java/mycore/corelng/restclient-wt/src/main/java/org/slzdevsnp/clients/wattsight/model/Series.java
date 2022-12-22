package org.slzdevsnp.clients.wattsight.model;

import lombok.*;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Series {
    private Integer id;
    private String name;
    private String timeZone;
    private String frequency;
    private List<Double[]> points;
    private ZonedDateTime modified;


    public Points getPointsAsEntity() {
        if (points == null) {
            return null;
        }
        return Points.from(points);
    }
}
