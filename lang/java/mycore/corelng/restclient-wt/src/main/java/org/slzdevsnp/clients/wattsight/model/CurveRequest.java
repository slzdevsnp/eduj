package org.slzdevsnp.clients.wattsight.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CurveRequest {
    private String query;
    @Singular("id")
    private List<Integer> ids;
    @Singular("name")
    private List<String> names;
    @Singular("commodity")
    private List<String> commodities;
    @Singular("category")
    private List<String> categories;
    @Singular("area")
    private List<String> areas;
    @Singular("station")
    private List<String> stations;
    @Singular("source")
    private List<String> sources;
    @Singular("scenario")
    private List<String> scenarios;
    @Singular("unit")
    private List<String> units;
    @Singular("timeZone")
    private List<String> timeZones;
    @Singular("version")
    private List<String> versions;
    @Singular("frequency")
    private List<String> frequencies;
    @Singular("dataType")
    private List<String> dataTypes;
    @Singular("curveState")
    private List<String> curveStates;

    private LocalDateTime modifiedSince;
    private Integer limit;
    private Integer offset;
    private Boolean onlyAccessible;

}
