package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CurrencyTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testCurrencySettersViaV1Alpha() {
        Response response = given().when().get("/v1/alpha/US");
        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testCurrencySettersViaV1Currency() {
        Response response = given().when().get("/v1/currency/USD");
        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testCurrencySettersViaV2Currency() {
        Response response = given().when().get("/v2/currency/EUR");
        response.then().statusCode(404);
    }
}