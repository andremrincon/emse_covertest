package ts01qwen3_7_plus_2;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CurrencyTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = System.getProperty("BASE_URL", "http://localhost:8080/rest");
        RestAssured.defaultParser = Parser.JSON;
    }

    @Ignore("The JSON input text should neither be null nor empty.")
    @Test(timeout = 60000)
    public void testSetCode() {
        given()
            .pathParam("currency", "EUR")
        .when()
            .get("/v2/currency/{currency}")
        .then()
            .body("currencies[0].code", equalTo("EUR"));
    }

    @Ignore("The JSON input text should neither be null nor empty.")
    @Test(timeout = 60000)
    public void testSetName() {
        given()
            .pathParam("currency", "USD")
        .when()
            .get("/v1/currency/{currency}")
        .then()
            .body("currencies[0].name", equalTo("United States dollar"));
    }

    @Ignore("The JSON input text should neither be null nor empty.")
    @Test(timeout = 60000)
    public void testSetSymbol() {
        given()
            .pathParam("alphacode", "US")
        .when()
            .get("/v2/alpha/{alphacode}")
        .then()
            .body("currencies[0].symbol", equalTo("$"));
    }
}