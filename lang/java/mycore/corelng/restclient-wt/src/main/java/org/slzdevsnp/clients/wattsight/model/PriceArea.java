package org.slzdevsnp.clients.wattsight.model;

import lombok.*;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PriceArea {
    private String key;
    private String name;
    private String description;
    private String parent;
}
