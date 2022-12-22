package org.slzdevsnp.clients.wattsight;

import org.slzdevsnp.clients.wattsight.model.Curve;
import org.slzdevsnp.clients.wattsight.model.CurveRequest;
import org.slzdevsnp.restclients.common.model.ClientListResponse;
import org.slzdevsnp.restclients.common.model.RestRequest;
import org.slzdevsnp.restclients.common.model.RestResponse;
import org.slzdevsnp.restclients.common.utils.UriBuilder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CurvesClient {
    private WattsightClient wattsightClient;

    protected CurvesClient(WattsightClient wattsightClient) {
        this.wattsightClient = wattsightClient;
    }


    public CurveResponses curve(
            CurveRequest curveRequest
    ) {
        UriBuilder builder = UriBuilder.url(
                "https://api.wattsight.com/api/curves"
        )
                .param("query", curveRequest.getQuery())
                .param("id", curveRequest.getIds())
                .param("name", curveRequest.getNames())
                .param("commodity", curveRequest.getCommodities())
                .param("category", curveRequest.getCategories())
                .param("area", curveRequest.getAreas())
                .param("station", curveRequest.getStations())
                .param("source", curveRequest.getSources())
                .param("scenario", curveRequest.getScenarios())
                .param("unit", curveRequest.getUnits())
                .param("time_zone", curveRequest.getTimeZones())
                .param("version", curveRequest.getVersions())
                .param("frequency", curveRequest.getFrequencies())
                .param("data_type", curveRequest.getDataTypes())
                .param("curve_state", curveRequest.getCurveStates())
                .param("modified_since", curveRequest.getModifiedSince())
                .param("limit", curveRequest.getLimit())
                .param("offset", curveRequest.getOffset())
                .param("only_accessible", curveRequest.getOnlyAccessible());

        RestResponse restResponse = wattsightClient.restCall(
                RestRequest.builder()
                        .url(builder.build())
                        .method("GET")
                        .build()
        );

        CurveResponses r = new CurveResponses();

        wattsightClient.parseResponse(
                restResponse,
                r,
                Curve[].class
        );
        return r;
    }


    public static class CurveResponses extends ClientListResponse<Curve> {
    }
}
