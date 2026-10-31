package ts01qwen3_7_plus_3;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CurrencyTest {

    @Test(timeout = 60000)
    public void testV2AlphaCodeCurrencySetters() {
        given()
            .baseUri("http://localhost:8080/rest")
        .when()
            .get("/v2/alpha/US")
        .then()
            .body("currencies[0].code", equalTo("USD"));
    }

    @Test(timeout = 60000)
    public void testV2CurrencyEndpointSetters() {
        given()
            .baseUri("http://localhost:8080/rest")
        .when()
            .get("/v2/currency/EUR")
        .then()
            .body("[0].currencies[0].code", equalTo("EUR"));
    }

    @Test(timeout = 60000)
    public void testV2NameEndpointSetters() {
        given()
            .baseUri("http://localhost:8080/rest")
        .when()
            .get("/v2/name/Germany")
        .then()
            .body("[0].currencies[0].code", equalTo("EUR"));
    }
}