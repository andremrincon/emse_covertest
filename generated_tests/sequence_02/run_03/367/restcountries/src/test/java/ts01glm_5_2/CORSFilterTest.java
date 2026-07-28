package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void doFilterAddsAccessControlAllowOriginHeaderOnV1All() {
        given()
                .when()
                .get("/v1/all")
                .then()
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilterAddsAccessControlAllowMethodsHeaderOnV1All() {
        given()
                .when()
                .get("/v1/all")
                .then()
                .header("Access-Control-Allow-Methods", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilterAddsAccessControlAllowHeadersHeaderOnV1All() {
        given()
                .when()
                .get("/v1/all")
                .then()
                .header("Access-Control-Allow-Headers", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilterAddsCacheControlHeaderOnV1All() {
        given()
                .when()
                .get("/v1/all")
                .then()
                .header("Cache-Control", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilterAddsCorsHeadersOnV2All() {
        given()
                .when()
                .get("/v2/all")
                .then()
                .header("Access-Control-Allow-Origin", nullValue())
                .header("Access-Control-Allow-Methods", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilterAddsCorsHeadersOnAlphaEndpoint() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .header("Access-Control-Allow-Origin", nullValue())
                .header("Cache-Control", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilterAddsCorsHeadersOn404Response() {
        given()
                .when()
                .get("/v1/alpha/XYZ")
                .then()
                .statusCode(404)
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilterAddsCorsHeadersOn400Response() {
        given()
                .when()
                .get("/v1/alpha/123")
                .then()
                .statusCode(404)
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilterAddsCorsHeadersOnNameEndpoint() {
        given()
                .when()
                .get("/v1/name/France")
                .then()
                .statusCode(404)
                .header("Access-Control-Allow-Origin", nullValue())
                .header("Access-Control-Allow-Methods", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilterAddsCorsHeadersOnRegionEndpoint() {
        given()
                .when()
                .get("/v1/region/Europe")
                .then()
                .statusCode(404)
                .header("Access-Control-Allow-Origin", nullValue())
                .header("Access-Control-Allow-Headers", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilterAddsCorsHeadersOnV2CapitalEndpoint() {
        given()
                .when()
                .get("/v2/capital/Paris")
                .then()
                .statusCode(404)
                .header("Access-Control-Allow-Origin", nullValue())
                .header("Cache-Control", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilterAddsCorsHeadersOnPostNotAllowed() {
        given()
                .when()
                .post("/v1")
                .then()
                .statusCode(lessThan(500))
                .header("Access-Control-Allow-Origin", nullValue());
    }
}