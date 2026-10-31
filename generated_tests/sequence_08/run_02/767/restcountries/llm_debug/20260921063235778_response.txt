package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CurrencyTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost:8080/rest";
    }

    @Test(timeout = 60000)
    public void testCurrencySettersViaV2Currency() {
        given()
            .when()
                .get("/v2/currency/EUR")
            .then()
                .statusCode(200);
    }
}