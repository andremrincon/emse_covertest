package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CurrencyTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080/rest";
        }
    }

    @Test(timeout = 60000)
    public void testGetCountryByAlphaCodeV1() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetCountryByCurrencyV1() {
        given()
            .when()
                .get("/v1/currency/USD")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetCountryByAlphaCodeV2() {
        given()
            .when()
                .get("/v2/alpha/US")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetCountryByCurrencyV2() {
        given()
            .when()
                .get("/v2/currency/EUR")
            .then()
                .statusCode(200);
    }
}