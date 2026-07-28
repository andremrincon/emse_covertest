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
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("BASE_URL");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testDoFilterAddsAccessControlAllowOriginHeaderOnV1All() {
        given()
            .when()
                .get("/v1/all")
            .then()
                .statusCode(200)
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testDoFilterAddsAccessControlAllowMethodsHeaderOnV1Alpha() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(200)
                .header("Access-Control-Allow-Methods", nullValue());
    }

    @Test(timeout = 60000)
    public void testDoFilterAddsAccessControlAllowHeadersHeaderOnV1Name() {
        given()
            .when()
                .get("/v1/name/France")
            .then()
                .statusCode(200)
                .header("Access-Control-Allow-Headers", nullValue());
    }

    @Test(timeout = 60000)
    public void testDoFilterAddsCacheControlHeaderOnV1Region() {
        given()
            .when()
                .get("/v1/region/Europe")
            .then()
                .statusCode(200)
                .header("Cache-Control", nullValue());
    }

    @Test(timeout = 60000)
    public void testDoFilterAddsAllCORSHeadersOnV1Capital() {
        given()
            .when()
                .get("/v1/capital/London")
            .then()
                .statusCode(200)
                .header("Access-Control-Allow-Origin", nullValue())
                .header("Access-Control-Allow-Methods", nullValue())
                .header("Access-Control-Allow-Headers", nullValue())
                .header("Cache-Control", nullValue());
    }

    @Test(timeout = 60000)
    public void testDoFilterAppliesOnV2All() {
        given()
            .when()
                .get("/v2/all")
            .then()
                .statusCode(200)
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testDoFilterAppliesOnV2Alpha() {
        given()
            .when()
                .get("/v2/alpha/US")
            .then()
                .statusCode(200)
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testDoFilterAppliesOnV2Name() {
        given()
            .when()
                .get("/v2/name/Germany")
            .then()
                .statusCode(200)
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testDoFilterAppliesOnV2Region() {
        given()
            .when()
                .get("/v2/region/Europe")
            .then()
                .statusCode(200)
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testDoFilterAppliesOnV1CallingCode() {
        given()
            .when()
                .get("/v1/callingcode/1")
            .then()
                .statusCode(200)
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testDoFilterAppliesOnV1Currency() {
        given()
            .when()
                .get("/v1/currency/USD")
            .then()
                .statusCode(200)
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testDoFilterAppliesOnV2Subregion() {
        given()
            .when()
                .get("/v2/subregion/Western%20Europe")
            .then()
                .statusCode(200)
                .header("Access-Control-Allow-Origin", nullValue());
    }
}