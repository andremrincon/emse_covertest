package ts01qwen3_7_plus;

import org.junit.Test;

import static io.restassured.RestAssured.given;

public class NotFoundExceptionMapperTest {

    @Test(timeout = 60000)
    public void testNotFoundExceptionMapper() {
        String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080/rest";

        given()
            .baseUri(baseUrl)
        .when()
            .get("/v1/alpha/XYZ")
        .then()
            .statusCode(404);
    }
}