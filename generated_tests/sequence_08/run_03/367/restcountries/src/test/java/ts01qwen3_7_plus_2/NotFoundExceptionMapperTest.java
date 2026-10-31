package ts01qwen3_7_plus_2;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class NotFoundExceptionMapperTest {

    @Test(timeout = 60000)
    public void testNotFoundExceptionMapperWithInvalidAlphaCode() {
        given()
            .baseUri(System.getProperty("base.url", "http://localhost:8080/rest"))
        .when()
            .get("/v1/alpha/XYZ")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testNotFoundExceptionMapperWithInvalidName() {
        given()
            .baseUri(System.getProperty("base.url", "http://localhost:8080/rest"))
        .when()
            .get("/v1/name/123")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testNotFoundExceptionMapperWithInvalidCurrency() {
        given()
            .baseUri(System.getProperty("base.url", "http://localhost:8080/rest"))
        .when()
            .get("/v1/currency/XYZ")
        .then()
            .statusCode(404);
    }
}