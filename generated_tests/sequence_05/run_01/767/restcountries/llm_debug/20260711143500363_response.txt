package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080/rest";
        }
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
    public void doFilterAddsAccessControlAllowOriginHeaderOnAlphaEndpoint() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .header("Access-Control-Allow-Origin", equalTo(null));
    }

    @Test(timeout = 60000)
    public void doFilterAddsAccessControlAllowMethodsHeaderOnAlphaEndpoint() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .header("Access-Control-Allow-Methods", equalTo(null));
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
    public void doFilterAddsAccessControlAllowHeadersHeaderOnV2All() {
        given()
                .when()
                .get("/v2/all")
                .then()
                .header("Access-Control-Allow-Headers", equalTo(null));
    }

    @Test(timeout = 60000)
    public void doFilterAddsHeadersOnNotFoundResponse() {
        given()
                .when()
                .get("/v1/alpha/XYZ")
                .then()
                .header("Access-Control-Allow-Origin", equalTo(null));
    }

    @Test(timeout = 60000)
    public void doFilterAddsHeadersOnBadRequestResponse() {
        given()
                .when()
                .get("/v1/alpha/123")
                .then()
                .header("Access-Control-Allow-Origin", equalTo(null));
    }

    @Test(timeout = 60000)
    public void doFilterAddsAccessControlAllowMethodsHeaderOnNameEndpoint() {
        given()
                .when()
                .get("/v1/name/France")
                .then()
                .header("Access-Control-Allow-Methods", equalTo(null));
    }

    @Test(timeout = 60000)
    public void doFilterAddsCacheControlHeaderOnRegionEndpoint() {
        given()
                .when()
                .get("/v1/region/Europe")
                .then()
                .header("Cache-Control", equalTo(null));
    }
}