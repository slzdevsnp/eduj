package org.slzdevsnp.clients.wattsight;

import org.slzdevsnp.clients.wattsight.model.Instance;
import org.slzdevsnp.restclients.common.model.ClientListResponse;
import org.slzdevsnp.restclients.common.model.RestRequest;
import org.slzdevsnp.restclients.common.model.RestResponse;
import org.slzdevsnp.restclients.common.utils.UriBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Validate;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.List;

@Slf4j
public class InstancesClient {
    private WattsightClient wattsightClient;

    protected InstancesClient(WattsightClient wattsightClient) {
        this.wattsightClient = wattsightClient;
    }


    public InstanceResponse instance(
            InstanceRequest request
    ) {
        Validate.notNull(request.getId(), "Id cannot be null");


        UriBuilder builder = UriBuilder.url(
                "https://api.wattsight.com/api/instances/" + request.getId()
        )
                .param("issue_date_from", request.getIssueDateFrom())
                .param("issue_date_to", request.getIssueDateTo())
                .param("issue_date", request.getIssueDates())
                .param("issue_weekday", request.getIssueWeekdays())
                .param("issue_day", request.getIssueDays())
                .param("issue_month", request.getIssueMonths())
                .param("issue_time", request.getIssueTimes())
                .param("with_data", request.getWithData())
                .param("data_from", request.getDataFrom())
                .param("data_to", request.getDataTo())
                .param("modified_since", request.getModifiedSince())
                .param("time_zone", request.getTimeZone())
                .param("filter", request.getFilter())
                .param("function", request.getFunction())
                .param("frequency", request.getFrequency())
                .param("output_time_zone", request.getOutputTimeZone());

        RestResponse restResponse = wattsightClient.restCall(
                RestRequest.builder()
                        .url(builder.build())
                        .method("GET")
                        .build()
        );
        InstanceResponse r = new InstanceResponse();

        wattsightClient.parseResponse(restResponse, r, Instance[].class);

        return r;
    }


    @Data
    @ToString
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class InstanceRequest {

        private Integer id;
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

    public static class InstanceResponse extends ClientListResponse<Instance> {
    }
}
