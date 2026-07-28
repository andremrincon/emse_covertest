package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CurrencyTest {

    @Test(timeout = 60000)
    public void testV1AlphaCode() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV1Currency() {
        given()
            .when()
                .get("/v1/currency/USD")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2AlphaCode() {
        given()
            .when()
                .get("/v2/alpha/US")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2Currency() {
        given()
            .when()
                .get("/v2/currency/EUR")
            .then()
                .statusCode(404);
    }

}