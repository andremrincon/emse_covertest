package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class RegexTest {

    private static final String BASE_URL = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";

    @Test(timeout = 60000)
    public void testUrlPatternMatch() {
        given()
            .pathParam("txt", "http://a/b")
        .when()
            .get(BASE_URL + "/api/pat/{txt}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testDatePatternMatch() {
        given()
            .pathParam("txt", "mon01jan")
        .when()
            .get(BASE_URL + "/api/pat/{txt}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFpePatternMatch() {
        given()
            .pathParam("txt", "12.34e+56")
        .when()
            .get(BASE_URL + "/api/pat/{txt}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoPatternMatch() {
        given()
            .pathParam("txt", "hello")
        .when()
            .get(BASE_URL + "/api/pat/{txt}")
        .then()
            .statusCode(200);
    }
}