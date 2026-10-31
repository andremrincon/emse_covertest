package ts01qwen3_7_plus_2;

import org.junit.Test;

import static io.restassured.RestAssured.given;

public class NotFoundExceptionMapperTest {

    @Test(timeout = 60000)
    public void testNotFoundExceptionMapperReturns404ForNonExistentAlphaCode() {
        String baseUrl = System.getProperty("test.base.url", "http://localhost:8080/rest");

        given()
            .baseUri(baseUrl)
        .when()
            .get("/v1/alpha/XYZ")
        .then()
            .statusCode(404);
    }
}