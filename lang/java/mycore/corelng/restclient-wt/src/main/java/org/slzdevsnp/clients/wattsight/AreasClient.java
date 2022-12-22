package org.slzdevsnp.clients.wattsight;

import org.slzdevsnp.clients.wattsight.model.PriceArea;
import org.slzdevsnp.restclients.common.model.ClientListResponse;
import org.slzdevsnp.restclients.common.model.RestRequest;
import org.slzdevsnp.restclients.common.model.RestResponse;
import org.slzdevsnp.restclients.common.utils.UriBuilder;

public class AreasClient {
    private WattsightClient wattsightClient;

    protected AreasClient(WattsightClient wattsightClient) {
        this.wattsightClient = wattsightClient;
    }

    public PriceAreas areas() {
        UriBuilder builder = UriBuilder.url(
                "https://api.wattsight.com/api/areas");

        RestResponse restResponse = wattsightClient.restCall(
                RestRequest.builder()
                        .url(builder.build())    // returns a string
                        .method("GET")
                        .build()
        );
        PriceAreas r = new PriceAreas();
        //a list of json objects returned
        //parseRespones parses restReponse to fill an array PriceArea[]
        wattsightClient.parseResponse(restResponse,
                                         r,
                                         PriceArea[].class);
        return r;

    }

    public static class PriceAreas extends ClientListResponse<PriceArea> {

    }
}
