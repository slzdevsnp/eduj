package org.slzdevsnp.clients.wattsight;


import org.slzdevsnp.clients.wattsight.model.Series;
import org.slzdevsnp.restclients.common.model.ClientSingleResponse;
import org.slzdevsnp.restclients.common.model.RestRequest;
import org.slzdevsnp.restclients.common.model.RestResponse;
import org.slzdevsnp.restclients.common.utils.UriBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Validate;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.List;

@Slf4j
public class SeriesClient {
    private WattsightClient wattsightClient;

    protected SeriesClient(WattsightClient wattsightClient) {
        this.wattsightClient = wattsightClient;
    }


    public SeriesResponse series(
            SeriesRequest request
    ) {
        Validate.notNull(request.getId(), "Id cannot be null");


        UriBuilder builder = UriBuilder.url(
                "https://api.wattsight.com/api/series/" + request.getId()
        )
                .param("from", request.getFrom())
                .param("to", request.getTo())
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
        SeriesResponse r = new SeriesResponse();

        wattsightClient.parseResponse(restResponse, r, Series.class);

        return r;
    }


    @Data
    @ToString
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SeriesRequest {
        private Integer id;
        private ZonedDateTime from;
        private ZonedDateTime to;
        private String timeZone;
        private List<String> filter;
        private String function;
        private String frequency;
        private String outputTimeZone;
    }

    public static class SeriesResponse extends ClientSingleResponse<Series> {
    }
}
