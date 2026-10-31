package ts01qwen3_7_plus_3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ResponseEntityTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testResponseEntityViaNameEndpoint() {
        Response response = given()
                .when()
                .get("/v1/name/123");

        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testResponseEntityViaCapitalEndpoint() {
        Response response = given()
                .when()
                .get("/v1/capital/123");

        response.then().statusCode(404);
    }
}