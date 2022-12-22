package org.slzdevsnp.clients.wattsight.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Curve {
    private Long id;
    private String name;
    private String commodity;
    private String description;
    private String area;
    private String borderSource;
    private String unit;
    private String station;
    private String version;
    private String frequency;
    private String timeZone;
    private String dataType;
    private String curveType;
    private String curveState;
    private List<String> categories;
    private List<String> sources;
    private List<String> scenarios;
    @JsonProperty("hasAccess") //the one thing that is not snake case
    private Boolean hasAccess;
    @JsonProperty("accessRange") //the one thing that is not snake case
    private Range accessRange;

    private ZonedDateTime created;
    private ZonedDateTime modified;
    private String issueFrequency;
}
