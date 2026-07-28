package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class NotyPevarTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testNotyPevarPath1() {
        given()
            .pathParam("i", 28)
            .pathParam("s", "world")
        .when()
            .get("/api/notypevar/{i}/{s}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testNotyPevarPath2() {
        given()
            .pathParam("i", 7)
            .pathParam("s", "a")
        .when()
            .get("/api/notypevar/{i}/{s}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testNotyPevarPath3() {
        given()
            .pathParam("i", 2)
            .pathParam("s", "world")
        .when()
            .get("/api/notypevar/{i}/{s}")
        .then()
            .statusCode(404);
    }
}