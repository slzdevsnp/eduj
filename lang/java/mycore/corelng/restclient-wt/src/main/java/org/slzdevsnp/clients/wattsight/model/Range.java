package org.slzdevsnp.clients.wattsight.model;

import lombok.*;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Range {
    private Boolean empty;
    private String begin;
    private String end;
}
