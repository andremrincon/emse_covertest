package ts01qwen3_7_plus_3;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class RegexTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("test.base.url");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("BASE_URL");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testUrlMatch() {
        given()
            .pathParam("txt", "http://a/b")
            .when()
            .get("/api/pat/{txt}")
            .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testDateMatch() {
        given()
            .pathParam("txt", "mon01jan")
            .when()
            .get("/api/pat/{txt}")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFpeMatch() {
        given()
            .pathParam("txt", "1.2e+34")
            .when()
            .get("/api/pat/{txt}")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneMatch() {
        given()
            .pathParam("txt", "hello")
            .when()
            .get("/api/pat/{txt}")
            .then()
            .statusCode(200);
    }
}