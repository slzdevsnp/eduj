package org.slzdevsnp.clients.wattsight;

import org.slzdevsnp.clients.wattsight.model.InstanceTaggedRequest;
import org.slzdevsnp.clients.wattsight.model.TaggedInstance;
import org.slzdevsnp.restclients.common.model.ClientListResponse;
import org.slzdevsnp.restclients.common.model.RestRequest;
import org.slzdevsnp.restclients.common.model.RestResponse;
import org.slzdevsnp.restclients.common.utils.UriBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Validate;

@Slf4j
public class TaggedInstancesClient {
    private WattsightClient wattsightClient;

    protected TaggedInstancesClient(WattsightClient wattsightClient) {
        this.wattsightClient = wattsightClient;
    }


    public TaggedInstances tagged(
            InstanceTaggedRequest request
    ) {
        Validate.notNull(request.getId(), "Id cannot be null");


        UriBuilder builder = UriBuilder.url(
                "https://api.wattsight.com/api/instances/tagged/" + request.getId()
        )
                .param("tag", request.getTags())
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
        TaggedInstances r = new TaggedInstances();

        wattsightClient.parseResponse(restResponse, r, TaggedInstance[].class);

        return r;
    }


    public static class TaggedInstances extends ClientListResponse<TaggedInstance> {
    }
}
