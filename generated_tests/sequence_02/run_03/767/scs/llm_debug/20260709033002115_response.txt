package ts01qwen3_7_plus;

import org.junit.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class RegexTest {

    static {
        RestAssured.baseURI = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testUrlMatch() {
        given()
            .pathParam("txt", "http://abc/def")
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
            .pathParam("txt", "12.34e+56")
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