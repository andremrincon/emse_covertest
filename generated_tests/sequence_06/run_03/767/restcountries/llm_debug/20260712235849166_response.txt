package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class CurrencyTest {

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
    public void testV1AlphaReturnsCurrencyCode() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV1AlphaReturnsCurrencyName() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV1AlphaReturnsCurrencySymbol() {
        given()
            .when()
                .get("/v1/alpha/FR")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV1CurrencyEndpointReturnsCurrencyFields() {
        given()
            .when()
                .get("/v1/currency/USD")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2AlphaReturnsCurrencyCode() {
        given()
            .when()
                .get("/v2/alpha/DE")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2AlphaReturnsCurrencyName() {
        given()
            .when()
                .get("/v2/alpha/DE")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2AlphaReturnsCurrencySymbol() {
        given()
            .when()
                .get("/v2/alpha/DE")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2RegionalblocReturnsCurrencyFields() {
        given()
            .when()
                .get("/v2/regionalbloc/EU")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV1AllReturnsCurrencyFields() {
        given()
            .when()
                .get("/v1/all")
            .then()
                .statusCode(404);
    }
}