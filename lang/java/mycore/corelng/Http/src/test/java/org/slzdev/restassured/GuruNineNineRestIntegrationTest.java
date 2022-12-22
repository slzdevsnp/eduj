package org.slzdev.restassured;

import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GuruNineNineRestIntegrationTest {

    private static final String endpoint="http://demo.guru99.com/V4/sinkministatement.php";
    private static final String url1 = "http://demo.guru99.com/V4/sinkministatement.php?CUSTOMER_ID=68195&PASSWORD=1234!&Account_No=1";


    @Test
    public void givenUrlOneGetRespoinseBody(){
        given()
                .when()
                .get(url1)
                .then()
                .log()
                .all();



    }

}

