package org.slzdevsnp.clients.wattsight.model;

import lombok.*;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CurveAttribute {
    private String key;
    private String name;
    private String description;

}
