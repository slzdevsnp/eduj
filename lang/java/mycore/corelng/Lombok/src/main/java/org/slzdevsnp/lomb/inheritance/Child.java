package org.slzdevsnp.lomb.inheritance;

import lombok.*;

@Data
@Setter(AccessLevel.NONE)
@NoArgsConstructor(access=AccessLevel.PRIVATE)
public class Child extends  Parent{
    private String cd;

    @Builder
    public Child (String pa, Integer pi, String cd){
        super(pa,pi);
        this.cd = cd;
    }

}
