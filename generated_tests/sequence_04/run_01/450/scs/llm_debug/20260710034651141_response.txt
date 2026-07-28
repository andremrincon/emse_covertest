package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class NotyPevarTest {

    static {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null) {
            baseUrl = System.getProperty("base.url", "http://localhost:8080");
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testNotyPevar_i28_sA() {
        given()
            .pathParam("i", 28)
            .pathParam("s", "a")
        .when()
            .get("/api/notypevar/{i}/{s}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNotyPevar_i7_sWorld() {
        given()
            .pathParam("i", 7)
            .pathParam("s", "world")
        .when()
            .get("/api/notypevar/{i}/{s}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNotyPevar_i2_sA() {
        given()
            .pathParam("i", 2)
            .pathParam("s", "a")
        .when()
            .get("/api/notypevar/{i}/{s}")
        .then()
            .statusCode(200);
    }
}