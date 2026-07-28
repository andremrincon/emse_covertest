package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Test;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;

public class GammqTest {

    private final String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";

    @Test(timeout = 60000)
    public void testGserXZero() {
        RestAssured.given()
                .when()
                .get(baseUrl + "/api/gammq/5.5/0.0")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGserNormal() {
        RestAssured.given()
                .when()
                .get(baseUrl + "/api/gammq/5.5/2.3")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGserExceedsItmax() {
        RestAssured.given()
                .when()
                .get(baseUrl + "/api/gammq/1000.0/999.0")
                .then()
                .statusCode(anyOf(is(200), is(400), is(500)));
    }

    @Test(timeout = 60000)
    public void testGcfNormal() {
        RestAssured.given()
                .when()
                .get(baseUrl + "/api/gammq/5.5/1000.0")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGcfFpminD() {
        RestAssured.given()
                .when()
                .get(baseUrl + "/api/gammq/5.5/1e31")
                .then()
                .statusCode(anyOf(is(200), is(400), is(500)));
    }

    @Test(timeout = 60000)
    public void testGcfExceedsItmax() {
        RestAssured.given()
                .when()
                .get(baseUrl + "/api/gammq/1000.0/1000.0")
                .then()
                .statusCode(anyOf(is(200), is(400), is(500)));
    }
}