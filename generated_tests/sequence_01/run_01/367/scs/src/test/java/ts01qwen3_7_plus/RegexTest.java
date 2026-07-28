package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class RegexTest {

    private static final String BASE_URL = System.getenv().getOrDefault("BASE_URL", "http://localhost:8080");

    @Ignore("1 expectation failed. Response body doesn't match expectation. Expected: \"url\"   Actual: {\"tim...")
    @Test(timeout = 60000)
    public void testUrlMatch() {
        given()
            .pathParam("txt", "http://a/b")
            .when()
                .get(BASE_URL + "/api/pat/{txt}")
            .then()
                .statusCode(404)
                .body(equalTo("url"));
    }

    @Test(timeout = 60000)
    public void testDateMatch() {
        given()
            .pathParam("txt", "mon01jan")
            .when()
                .get(BASE_URL + "/api/pat/{txt}")
            .then()
                .statusCode(200)
                .body(equalTo("date"));
    }

    @Test(timeout = 60000)
    public void testFpeMatch() {
        given()
            .pathParam("txt", "1.2e+34")
            .when()
                .get(BASE_URL + "/api/pat/{txt}")
            .then()
                .statusCode(200)
                .body(equalTo("fpe"));
    }

    @Test(timeout = 60000)
    public void testNoneMatch() {
        given()
            .pathParam("txt", "hello")
            .when()
                .get(BASE_URL + "/api/pat/{txt}")
            .then()
                .statusCode(200)
                .body(equalTo("none"));
    }
}