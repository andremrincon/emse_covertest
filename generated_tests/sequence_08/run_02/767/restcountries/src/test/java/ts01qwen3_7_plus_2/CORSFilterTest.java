package ts01qwen3_7_plus_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    private static final String BASE_URI = System.getenv("BASE_URI") != null ? System.getenv("BASE_URI") : "http://localhost:8080/rest";

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URI;
    }

    @Test(timeout = 60000)
    public void testCorsAllowOriginOnV1All() {
        given()
            .baseUri(BASE_URI)
        .when()
            .get("/v1/all")
        .then()
            .statusCode(200)
            .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testCorsAllowMethodsOnV2All() {
        given()
            .baseUri(BASE_URI)
        .when()
            .get("/v2/all")
        .then()
            .statusCode(200)
            .header("Access-Control-Allow-Methods", nullValue());
    }

    @Test(timeout = 60000)
    public void testCorsAllowHeadersOnV1Alpha() {
        given()
            .baseUri(BASE_URI)
        .when()
            .get("/v1/alpha/US")
        .then()
            .statusCode(200)
            .header("Access-Control-Allow-Headers", nullValue());
    }

    @Test(timeout = 60000)
    public void testCorsCacheControlOnV2Alpha() {
        given()
            .baseUri(BASE_URI)
        .when()
            .get("/v2/alpha/US")
        .then()
            .statusCode(200)
            .header("Cache-Control", nullValue());
    }

    @Test(timeout = 60000)
    public void testCorsAllowOriginOnV1Name() {
        given()
            .baseUri(BASE_URI)
        .when()
            .get("/v1/name/France")
        .then()
            .statusCode(200)
            .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testCorsAllowMethodsOnV2Name() {
        given()
            .baseUri(BASE_URI)
        .when()
            .get("/v2/name/Germany")
        .then()
            .statusCode(200)
            .header("Access-Control-Allow-Methods", nullValue());
    }

    @Test(timeout = 60000)
    public void testCorsAllowHeadersOnV1Capital() {
        given()
            .baseUri(BASE_URI)
        .when()
            .get("/v1/capital/London")
        .then()
            .statusCode(200)
            .header("Access-Control-Allow-Headers", nullValue());
    }

    @Test(timeout = 60000)
    public void testCorsCacheControlOnV2Capital() {
        given()
            .baseUri(BASE_URI)
        .when()
            .get("/v2/capital/Paris")
        .then()
            .statusCode(200)
            .header("Cache-Control", nullValue());
    }

    @Test(timeout = 60000)
    public void testCorsAllowOriginOnV1Region() {
        given()
            .baseUri(BASE_URI)
        .when()
            .get("/v1/region/Europe")
        .then()
            .statusCode(200)
            .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testCorsAllowMethodsOnV2Region() {
        given()
            .baseUri(BASE_URI)
        .when()
            .get("/v2/region/Africa")
        .then()
            .statusCode(200)
            .header("Access-Control-Allow-Methods", nullValue());
    }

    @Test(timeout = 60000)
    public void testCorsAllowHeadersOnV1Currency() {
        given()
            .baseUri(BASE_URI)
        .when()
            .get("/v1/currency/USD")
        .then()
            .statusCode(200)
            .header("Access-Control-Allow-Headers", nullValue());
    }

    @Test(timeout = 60000)
    public void testCorsCacheControlOnV2Currency() {
        given()
            .baseUri(BASE_URI)
        .when()
            .get("/v2/currency/EUR")
        .then()
            .statusCode(200)
            .header("Cache-Control", nullValue());
    }
}