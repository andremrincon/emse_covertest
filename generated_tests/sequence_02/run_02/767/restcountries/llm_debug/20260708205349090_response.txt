package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void doFilterAddsAccessControlAllowOriginHeaderOnV1All() {
        given()
                .when()
                .get("/v1/all")
                .then()
                .header("Access-Control-Allow-Origin", equalTo((Object) null));
    }

    @Test(timeout = 60000)
    public void doFilterAddsAccessControlAllowMethodsHeaderOnV1All() {
        given()
                .when()
                .get("/v1/all")
                .then()
                .header("Access-Control-Allow-Methods", equalTo((Object) null));
    }

    @Test(timeout = 60000)
    public void doFilterAddsAccessControlAllowHeadersHeaderOnV1All() {
        given()
                .when()
                .get("/v1/all")
                .then()
                .header("Access-Control-Allow-Headers", equalTo((Object) null));
    }

    @Test(timeout = 60000)
    public void doFilterAddsCacheControlHeaderOnV1All() {
        given()
                .when()
                .get("/v1/all")
                .then()
                .header("Cache-Control", equalTo((Object) null));
    }

    @Test(timeout = 60000)
    public void doFilterAddsAllCORSHeadersOnV2All() {
        given()
                .when()
                .get("/v2/all")
                .then()
                .header("Access-Control-Allow-Origin", equalTo((Object) null))
                .header("Access-Control-Allow-Methods", equalTo((Object) null))
                .header("Access-Control-Allow-Headers", equalTo((Object) null))
                .header("Cache-Control", equalTo((Object) null));
    }

    @Test(timeout = 60000)
    public void doFilterAddsCORSHeadersOnAlphaEndpoint() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(200)
                .header("Access-Control-Allow-Origin", equalTo((Object) null));
    }

    @Test(timeout = 60000)
    public void doFilterAddsCORSHeadersOnNameEndpoint() {
        given()
                .when()
                .get("/v1/name/France")
                .then()
                .header("Access-Control-Allow-Origin", equalTo((Object) null));
    }

    @Test(timeout = 60000)
    public void doFilterAddsCORSHeadersOnRegionEndpoint() {
        given()
                .when()
                .get("/v1/region/Europe")
                .then()
                .header("Access-Control-Allow-Origin", equalTo((Object) null));
    }

    @Test(timeout = 60000)
    public void doFilterAddsCORSHeadersOnCapitalEndpoint() {
        given()
                .when()
                .get("/v1/capital/London")
                .then()
                .header("Access-Control-Allow-Origin", equalTo((Object) null));
    }

    @Test(timeout = 60000)
    public void doFilterAddsCORSHeadersOnCurrencyEndpoint() {
        given()
                .when()
                .get("/v1/currency/USD")
                .then()
                .header("Access-Control-Allow-Origin", equalTo((Object) null));
    }

    @Test(timeout = 60000)
    public void doFilterAddsCORSHeadersOnCallingCodeEndpoint() {
        given()
                .when()
                .get("/v1/callingcode/1")
                .then()
                .header("Access-Control-Allow-Origin", equalTo((Object) null));
    }

    @Test(timeout = 60000)
    public void doFilterAddsCORSHeadersOnV2AlphaEndpoint() {
        given()
                .when()
                .get("/v2/alpha/US")
                .then()
                .header("Access-Control-Allow-Origin", equalTo((Object) null));
    }
}