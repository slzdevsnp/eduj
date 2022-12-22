package org.slzdevsnp.clients.wattsight.model;

import lombok.*;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InstanceTaggedRequest {
    private Integer id;
    @Singular("tag")
    private List<String> tags;
    private ZonedDateTime issueDateFrom;
    private ZonedDateTime issueDateTo;
    @Singular("issueDate")
    private List<ZonedDateTime> issueDates;
    @Singular("issueWeekday")
    private List<String> issueWeekdays;
    @Singular("issueDay")
    private List<Integer> issueDays;
    @Singular("issueMonth")
    private List<String> issueMonths;
    @Singular("issueTime")
    private List<String> issueTimes;
    private Boolean withData;
    private ZonedDateTime dataFrom;
    private ZonedDateTime dataTo;
    private ZonedDateTime modifiedSince;
    private String timeZone;
    private List<String> filter;
    private String function;
    private String frequency;
    private String outputTimeZone;

}
