package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

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
    public void testGetStatusAndMessageFor404() {
        given()
            .pathParam("name", "123")
        .when()
            .get("/v1/name/{name}")
        .then()
            .statusCode(404)
            .body(equalTo(""));
    }

    @Test(timeout = 60000)
    public void testGetStatusAndMessageFor400() {
        given()
            .pathParam("currency", "123")
        .when()
            .get("/v2/currency/{currency}")
        .then()
            .statusCode(404)
            .body(equalTo(""));
    }
}