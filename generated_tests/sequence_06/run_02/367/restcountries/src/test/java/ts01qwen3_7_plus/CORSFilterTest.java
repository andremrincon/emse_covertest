package ts01qwen3_7_plus;

import static io.restassured.RestAssured.given;
import org.junit.Test;

public class CORSFilterTest {

    private String getBaseUrl() {
        String baseUrl = System.getProperty("base.url");
        if (baseUrl == null) {
            baseUrl = System.getenv("BASE_URL");
        }
        if (baseUrl == null) {
            baseUrl = "http://localhost:8080/rest";
        }
        return baseUrl;
    }

    @Test(timeout = 60000)
    public void testV1All() {
        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/v1/all")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV1Alpha() {
        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/v1/alpha/US")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV1Name() {
        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/v1/name/France")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV1Currency() {
        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/v1/currency/USD")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV1Callingcode() {
        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/v1/callingcode/1")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV1Capital() {
        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/v1/capital/London")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV1Region() {
        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/v1/region/Europe")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV2All() {
        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/v2/all")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV2Alpha() {
        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/v2/alpha/US")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV2Name() {
        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/v2/name/Germany")
        .then()
            .statusCode(200);
    }
}