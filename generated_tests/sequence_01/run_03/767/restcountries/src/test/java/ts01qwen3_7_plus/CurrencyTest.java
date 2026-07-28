package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CurrencyTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testV1AlphaCodeReturnsCurrency() {
        Response response = given()
                .when()
                .get("/v1/alpha/US");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV1CurrencyReturnsCountries() {
        Response response = given()
                .when()
                .get("/v1/currency/USD");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV2AlphaCodeReturnsCurrency() {
        Response response = given()
                .when()
                .get("/v2/alpha/US");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV2CurrencyReturnsCountries() {
        Response response = given()
                .when()
                .get("/v2/currency/EUR");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV1AllReturnsCurrencies() {
        Response response = given()
                .when()
                .get("/v1/all");

        response.then().statusCode(200);
    }
}