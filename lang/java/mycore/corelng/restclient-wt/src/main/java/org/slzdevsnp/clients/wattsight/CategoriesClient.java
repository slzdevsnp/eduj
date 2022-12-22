package org.slzdevsnp.clients.wattsight;

import org.slzdevsnp.clients.wattsight.model.CurveAttribute;
import org.slzdevsnp.clients.wattsight.model.CurveAttributes;
import org.slzdevsnp.restclients.common.model.RestRequest;
import org.slzdevsnp.restclients.common.model.RestResponse;
import org.slzdevsnp.restclients.common.utils.UriBuilder;

public class CategoriesClient {
    private WattsightClient wattsightClient;

    protected CategoriesClient(WattsightClient wattsightClient) {
        this.wattsightClient = wattsightClient;
    }

    public CurveAttributes categories() {

        UriBuilder builder = UriBuilder.url(
                "https://api.wattsight.com/api/categories");

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
