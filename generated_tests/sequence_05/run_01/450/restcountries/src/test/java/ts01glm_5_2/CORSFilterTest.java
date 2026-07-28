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
                .header("Access-Control-Allow-Origin", equalTo(null));
    }

    @Test(timeout = 60000)
    public void doFilterAddsAccessControlAllowMethodsHeaderOnV1All() {
        given()
                .when()
                .get("/v1/all")
                .then()
                .header("Access-Control-Allow-Methods", equalTo(null));
    }

    @Test(timeout = 60000)
    public void doFilterAddsAccessControlAllowHeadersHeaderOnV1All() {
        given()
                .when()
                .get("/v1/all")
                .then()
                .header("Access-Control-Allow-Headers", equalTo(null));
    }

    @Test(timeout = 60000)
    public void doFilterAddsCacheControlHeaderOnV1All() {
        given()
                .when()
                .get("/v1/all")
                .then()
                .header("Cache-Control", equalTo(null));
    }

    @Test(timeout = 60000)
    public void doFilterAddsAccessControlAllowOriginHeaderOnV2All() {
        given()
                .when()
                .get("/v2/all")
                .then()
                .header("Access-Control-Allow-Origin", equalTo(null));
    }

    @Test(timeout = 60000)
    public void doFilterAddsAccessControlAllowMethodsHeaderOnV2All() {
        given()
                .when()
                .get("/v2/all")
                .then()
                .header("Access-Control-Allow-Methods", equalTo(null));
    }

    @Test(timeout = 60000)
    public void doFilterAddsAccessControlAllowHeadersHeaderOnV2All() {
        given()
                .when()
                .get("/v2/all")
                .then()
                .header("Access-Control-Allow-Headers", equalTo(null));
    }

    @Test(timeout = 60000)
    public void doFilterAddsCacheControlHeaderOnV2All() {
        given()
                .when()
                .get("/v2/all")
                .then()
                .header("Cache-Control", equalTo(null));
    }

    @Test(timeout = 60000)
    public void doFilterAddsAccessControlAllowOriginHeaderOnAlphaEndpoint() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .header("Access-Control-Allow-Origin", equalTo(null));
    }

    @Test(timeout = 60000)
    public void doFilterAddsCacheControlHeaderOnNameEndpoint() {
        given()
                .when()
                .get("/v1/name/France")
                .then()
                .header("Cache-Control", equalTo(null));
    }

    @Test(timeout = 60000)
    public void doFilterAddsAccessControlAllowMethodsHeaderOnRegionEndpoint() {
        given()
                .when()
                .get("/v1/region/Europe")
                .then()
                .header("Access-Control-Allow-Methods", equalTo(null));
    }

    @Test(timeout = 60000)
    public void doFilterAddsAccessControlAllowHeadersHeaderOnCapitalEndpoint() {
        given()
                .when()
                .get("/v1/capital/London")
                .then()
                .header("Access-Control-Allow-Headers", equalTo(null));
    }
}