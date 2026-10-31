package ts01qwen3_7_plus_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CurrencyTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseURI", "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void testCurrencySettersViaV1Alpha() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));

        given()
            .when().get("/v1/alpha/US")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCurrencySettersViaV1Currency() {
        given().when().get("/v1/currency/USD").then().statusCode(lessThan(300));

        given()
            .when().get("/v1/currency/USD")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCurrencySettersViaV2Currency() {
        given().when().get("/v2/currency/EUR").then().statusCode(lessThan(300));

        given()
            .when().get("/v2/currency/EUR")
            .then()
            .statusCode(200);
    }
}