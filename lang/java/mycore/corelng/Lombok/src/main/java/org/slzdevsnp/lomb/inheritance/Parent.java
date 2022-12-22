package org.slzdevsnp.lomb.inheritance;

import lombok.*;

@Data
@Setter(AccessLevel.NONE)
@AllArgsConstructor
@NoArgsConstructor(access=AccessLevel.PROTECTED)
public class Parent {
    private String pa;
    private Integer pi;
}
