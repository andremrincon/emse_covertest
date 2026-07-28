package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class NotyPevarTest {

    static {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testNoTypeVar_Branch1() {
        given()
            .pathParam("i", 28)
            .pathParam("s", "a")
        .when()
            .get("/api/notypevar/{i}/{s}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoTypeVar_Branch2() {
        given()
            .pathParam("i", 7)
            .pathParam("s", "z")
        .when()
            .get("/api/notypevar/{i}/{s}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoTypeVar_Branch3() {
        given()
            .pathParam("i", 0)
            .pathParam("s", "a")
        .when()
            .get("/api/notypevar/{i}/{s}")
        .then()
            .statusCode(200);
    }
}