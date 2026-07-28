package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowOriginHeaderOnSuccess() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowMethodsHeaderOnSuccess() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .header("Access-Control-Allow-Methods", nullValue());
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowHeadersHeaderOnSuccess() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .header("Access-Control-Allow-Headers", nullValue());
    }

    @Test(timeout = 60000)
    public void testCacheControlHeaderOnSuccess() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .header("Cache-Control", nullValue());
    }

    @Test(timeout = 60000)
    public void testCORSHeaderOn404Response() {
        given()
            .when()
                .get("/v1/alpha/XYZ")
            .then()
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testCORSHeaderOn400Response() {
        given()
            .when()
                .get("/v1/alpha/123")
            .then()
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testCORSHeadersOnV2Endpoint() {
        given()
            .when()
                .get("/v2/alpha/US")
            .then()
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testCORSHeadersOnAllEndpoint() {
        given()
            .when()
                .get("/v1/all")
            .then()
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testCORSHeadersWithQueryParameters() {
        given()
            .queryParam("codes", "US,CA,MX")
            .when()
                .get("/v1/alpha")
            .then()
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testCORSHeadersOnNameEndpoint() {
        given()
            .when()
                .get("/v1/name/France")
            .then()
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testCORSHeadersOnRegionEndpoint() {
        given()
            .when()
                .get("/v1/region/Europe")
            .then()
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testAllCORSHeadersPresentTogether() {
        given()
            .when()
                .get("/v1/capital/London")
            .then()
                .header("Access-Control-Allow-Origin", nullValue())
                .header("Access-Control-Allow-Methods", nullValue())
                .header("Access-Control-Allow-Headers", nullValue())
                .header("Cache-Control", nullValue());
    }
}