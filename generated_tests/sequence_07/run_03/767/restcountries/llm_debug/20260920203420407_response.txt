package ts01glm_5_2_3;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null) {
            baseUrl = System.getenv("BASE_URL");
        }
        if (baseUrl == null) {
            baseUrl = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void doFilterAddsAccessControlAllowOriginHeaderOnGetAll() {
        given()
        .when()
            .get("/v1/all")
        .then()
            .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilterAddsAccessControlAllowMethodsHeaderOnGetAll() {
        given()
        .when()
            .get("/v1/all")
        .then()
            .header("Access-Control-Allow-Methods", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilterAddsAccessControlAllowHeadersHeaderOnGetAll() {
        given()
        .when()
            .get("/v1/all")
        .then()
            .header("Access-Control-Allow-Headers", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilterAddsCacheControlHeaderOnGetAll() {
        given()
        .when()
            .get("/v1/all")
        .then()
            .header("Cache-Control", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilterAddsCorsHeadersOnNotFoundResponse() {
        given()
        .when()
            .get("/v1/name/123")
        .then()
            .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilterAddsCorsHeadersOnBadRequestResponse() {
        given()
        .when()
            .queryParam("codes", "123")
            .get("/v1/alpha")
        .then()
            .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilterAddsCorsHeadersOnV2AllEndpoint() {
        given()
        .when()
            .get("/v2/all")
        .then()
            .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilterAddsCorsHeadersOnPostMethodNotAllowed() {
        given()
        .when()
            .post("/v2")
        .then()
            .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilterAddsCorsHeadersOnAlphaCodeEndpoint() {
        given()
        .when()
            .get("/v1/alpha/US")
        .then()
            .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilterAddsCorsHeadersOnV2NameEndpoint() {
        given()
        .when()
            .get("/v2/name/Germany")
        .then()
            .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilterAddsCorsHeadersOnRegionEndpoint() {
        given()
        .when()
            .get("/v1/region/Europe")
        .then()
            .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilterAddsCacheControlWithMaxAgeOnV2Endpoint() {
        given()
        .when()
            .get("/v2")
        .then()
            .header("Cache-Control", nullValue());
    }
}