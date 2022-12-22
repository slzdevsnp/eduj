package org.slzdevsnp.clients.wattsight;

import org.slzdevsnp.clients.wattsight.model.CurveAttribute;
import org.slzdevsnp.clients.wattsight.model.CurveAttributes;
import org.slzdevsnp.restclients.common.model.RestRequest;
import org.slzdevsnp.restclients.common.model.RestResponse;
import org.slzdevsnp.restclients.common.utils.UriBuilder;

public class CurveTypesClient {
    private WattsightClient wattsightClient;

    protected CurveTypesClient(WattsightClient wattsightClient) {
        this.wattsightClient = wattsightClient;
    }

    public CurveAttributes curveTypes() {
        UriBuilder builder = UriBuilder.url(
                "https://api.wattsight.com/api/curve_types");

        RestResponse restResponse = wattsightClient.restCall(
                RestRequest.builder()
                        .url(builder.build())
                        .method("GET")
                        .build()
        );
        CurveAttributes r = new CurveAttributes();
        wattsightClient.parseResponse(restResponse, r, CurveAttribute[].class);
        return r;
    }
}
