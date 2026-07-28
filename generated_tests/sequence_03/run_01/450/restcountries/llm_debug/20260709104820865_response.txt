package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class NotFoundExceptionMapperTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("base.url");
        if (baseUrl == null) {
            baseUrl = System.getenv("BASE_URL");
        }
        if (baseUrl == null) {
            baseUrl = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testNotFoundExceptionMapperReturns404() {
        given()
            .when()
            .get("/v1/alpha/XYZ")
            .then()
            .statusCode(404);
    }
}