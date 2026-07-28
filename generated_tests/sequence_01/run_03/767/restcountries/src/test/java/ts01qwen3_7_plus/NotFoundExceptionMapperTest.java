package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class NotFoundExceptionMapperTest {

    @Test(timeout = 60000)
    public void testToResponseReturns404ForInvalidAlphaCode() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");

        given()
            .baseUri(baseUrl)
        .when()
            .get("/v1/alpha/XYZ")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testToResponseReturns404ForNonExistentPath() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");

        given()
            .baseUri(baseUrl)
        .when()
            .get("/v1/nonexistent")
        .then()
            .statusCode(404);
    }
}