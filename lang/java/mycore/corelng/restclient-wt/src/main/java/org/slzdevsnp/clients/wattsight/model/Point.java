package org.slzdevsnp.clients.wattsight.model;

import lombok.*;

import java.time.LocalDateTime;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Point {
    private LocalDateTime timestampUtc;
    private Double value;
}
