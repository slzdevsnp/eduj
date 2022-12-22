package org.slzdevsnp.clients.wattsight.model;

import lombok.*;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Instance {
    private Integer id;
    private String name;
    private String frequency;
    private String timeZone;
    private ZonedDateTime issueDate;
    private Boolean access;
    private List<Double[]> points;

    private ZonedDateTime created;
    private ZonedDateTime modified;

    public Points getPointsAsEntity() {
        if (points == null) {
            return null;
        }
        return Points.from(points);
    }
}
