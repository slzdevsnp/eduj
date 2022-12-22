package org.slzdevsnp.clients.wattsight;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slzdevsnp.clients.wattsight.model.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Properties;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Slf4j
public class WattsightClientTest {
    private WattsightClient client;

    @Before
    public void init() throws IOException {
        /// the below ensures the code in init() executes only once per TestSuite run
        if (this.client == null) {
            Properties properties = new Properties();
            properties.load(WattsightClientTest.class.getResourceAsStream("/application.properties"));

            String clientId = (String) properties.get("wattsight.clientId");
            String secret = (String) properties.get("wattsight.clientSecretId");
            ///.login() is a static method returning Wattsight instance
            this.client = WattsightClient.login(clientId, secret);
        }
    }

    @Test
    public void shouldLogin() {
        assertThat(client.getToken().getTokenType(), is("Bearer"));
        assertThat(client.getToken().getAccessToken(), is(not("")));
    }

    @Test
    public void shouldGetPriceArea() {
        AreasClient.PriceAreas areas = client.areasClient().areas();
        assertThat(areas.getEntity().size(), greaterThanOrEqualTo(1));
    }

    @Test
    public void shouldGetCategories() {
        CurveAttributes categories = client.categoriesClient().categories();
        assertThat(categories.getEntity().size(), is(greaterThanOrEqualTo(1)));
    }


    @Test
    public void shouldGetCommodities() {
        CurveAttributes categories = client.commoditiesClient().commodities();
        assertThat(categories.getEntity().size(), is(greaterThanOrEqualTo(1)));
    }

    @Test
    public void shouldGetCurveStates() {
        CurveAttributes curveStates = client.curveStatesClient().curveStates();
        assertThat(curveStates.getEntity().size(), is(greaterThanOrEqualTo(1)));
    }

    @Test
    public void shouldGetCurveTyypes() {
        CurveAttributes curveStates = client.curveTypesClient().curveTypes();
        assertThat(curveStates.getEntity().size(), is(greaterThanOrEqualTo(1)));
    }

    @Test
    public void shouldGetCurve() {
        CurvesClient.CurveResponses response = client.curvesClient().curve(
                CurveRequest.builder()
                        .id(1223)
                        .build()
        );
        assertThat(response.getHttpResponseCode(), is(200));
        assertThat(response.getRaw(), is(not("")));
        assertThat(response.getEntity(), hasSize(1));
        for (Curve curve : response.getEntity()) {
            log.debug("Curve - {}", curve);
        }
    }


    @Test
    public void shouldGetCurveTagged() {
        CurvesClient.CurveResponses response = client.curvesClient().curve(
                CurveRequest.builder()
                        .id(82229)
                        .build()
        );
        assertThat(response.getHttpResponseCode(), is(200));
        assertThat(response.getRaw(), is(not("")));
        assertThat(response.getEntity(), hasSize(1));
        for (Curve curve : response.getEntity()) {
            log.debug("Curve - {}", curve);
        }
    }

    @Ignore
    @Test
    public void shouldGetTaggedInstanceButHandleInvalidSelection() {
        TaggedInstancesClient.TaggedInstances response = client.taggedInstancesClient().tagged(
                InstanceTaggedRequest.builder()
                        .id(1223)
                        .build()
        );

     // assertThat(response.getHttpError(), is(Boolean.TRUE));
        assertThat(response.getRaw(), is(notNullValue()));
        assertThat(response.getHttpErrorMessage(), is(nullValue()));
        assertThat(response.getHttpResponseCode(), is(400));

    }

    @Ignore
    @Test
    public void shouldGetTaggedInstance() {
        TaggedInstancesClient.TaggedInstances response = client.taggedInstancesClient().tagged(
                InstanceTaggedRequest.builder()
                        .id(82211)
                        .tag("01")
                        .issueDate(ZonedDateTime.of(2020, 3, 8, 0, 0, 0, 0, ZoneId.of("CET")))
                        .withData(Boolean.TRUE)
                        .build()
        );

        assertThat(response.getHttpResponseCode(), is(200));

        assertThat(response.getRaw(), is(notNullValue()));
        assertThat(response.getHttpErrorMessage(), not(isEmptyString()));

    }



    @Ignore
    @Test
    public void test() {

        TaggedInstancesClient.TaggedInstances response = client.taggedInstancesClient().tagged(
                InstanceTaggedRequest.builder()
                        .id(82211)
                        .issueDateFrom(ZonedDateTime.of(2020, 1, 1, 1, 0, 0, 0, ZoneId.of("UTC")))
                        .issueDateTo(
                                ZonedDateTime.of(2020, 1, 2, 0, 59, 59, 999999, ZoneId.of("UTC"))
                        )
                        .build()
        );
        log.info("Tagged Response for Partition Query - {}", response.getRawJson().get(1).toString());
        log.info("Tag - {}", response.getRawJson().get(0).findPath("tag").asText());
        log.info("Tag - {}", response.getRawJson().get(0).findPath("issue_date").asText());
    }


    @Ignore
    @Test
    public void shouldGetTimeseriesType() {

        SeriesClient.SeriesResponse response = client.seriesClient()
                .series(
                        SeriesClient.SeriesRequest.builder()
                                .id(102585)
                                .build()
                );

        log.info("Series - {}", response.getEntity());
        log.info("Raw - {}", response.getRaw());

    }

    @Ignore
    @Test
    public void shouldGetTaggedInstanceWithData() {
        TaggedInstancesClient.TaggedInstances response = client.taggedInstancesClient().tagged(
                InstanceTaggedRequest.builder()
                        .id(82211)
                        .tag("01")
                        .withData(Boolean.TRUE)
                        .issueDate(ZonedDateTime.now().truncatedTo(ChronoUnit.DAYS).minusDays(2))
                        .build()
        );

        assertThat(response.getRaw(), is(notNullValue()));
        assertThat(response.getHttpErrorMessage(), not(isEmptyString()));
        assertThat(response.getEntity().get(0).getPoints(), hasSize(greaterThanOrEqualTo(1)));


        Points points = response.getEntity().get(0).getPointsAsEntity();
        assertThat(points.getPoints().get(0).getTimestampUtc(), is(LocalDateTime.now().truncatedTo(ChronoUnit.DAYS).minusDays(1).minusHours(1)));

    }
}