package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class NotFoundExceptionMapperTest {

    private String getBaseUrl() {
        return System.getProperty("baseUrl", "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void testNotFoundExceptionMapperForAlphaCode() {
        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/v1/alpha/XYZ")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testNotFoundExceptionMapperForName() {
        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/v2/name/123")
        .then()
            .statusCode(404);
    }
}