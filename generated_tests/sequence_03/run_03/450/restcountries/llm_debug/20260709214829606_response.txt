package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class NotFoundExceptionMapperTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", System.getenv().getOrDefault("BASE_URL", "http://localhost:8080/rest"));
    }

    @Test(timeout = 60000)
    public void testNotFoundExceptionMapperForMissingResource() {
        given()
            .pathParam("alphacode", "XYZ")
        .when()
            .get("/v1/alpha/{alphacode}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testNotFoundExceptionMapperForInvalidPath() {
        given()
        .when()
            .get("/v1/nonexistent")
        .then()
            .statusCode(404);
    }
}