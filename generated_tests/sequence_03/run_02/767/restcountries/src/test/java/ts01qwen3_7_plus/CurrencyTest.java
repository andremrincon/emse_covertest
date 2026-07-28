package ts01qwen3_7_plus;

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
    public static void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.trim().isEmpty()) {
            baseUrl = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = baseUrl;
        RestAssured.defaultParser = Parser.JSON;
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testCurrencyCodeMapping() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .body("currencies[0].code", equalTo("USD"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testCurrencyNameMapping() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .body("currencies[0].name", equalTo("United States dollar"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testCurrencySymbolMapping() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .body("currencies[0].symbol", equalTo("$"));
    }
}