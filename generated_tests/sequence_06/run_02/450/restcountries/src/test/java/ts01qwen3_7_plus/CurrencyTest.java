package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CurrencyTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.basePath = "/rest";
    }

    @Test(timeout = 60000)
    public void testV1AlphaCodeReturnsCurrency() {
        given()
        .when()
            .get("/v1/alpha/US")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV1CurrencyReturnsCurrency() {
        given()
        .when()
            .get("/v1/currency/USD")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV2CurrencyReturnsCurrency() {
        given()
        .when()
            .get("/v2/currency/EUR")
        .then()
            .statusCode(200);
    }
}