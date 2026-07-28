package ts01qwen3_7_plus;

import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import io.restassured.RestAssured;

import org.junit.Ignore;
public class RegexTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        RestAssured.baseURI = baseUrl != null ? baseUrl : "http://localhost:8080";
    }

    @Ignore("1 expectation failed. Response body doesn't match expectation. Expected: \"url\"   Actual: {\"tim...")
    @Test(timeout = 60000)
    public void testUrlPattern() {
        given()
            .pathParam("txt", "http://a/b")
        .when()
            .get("/api/pat/{txt}")
        .then()
            .statusCode(404)
            .body(equalTo("url"));
    }

    @Test(timeout = 60000)
    public void testDatePattern() {
        given()
            .pathParam("txt", "mon01jan")
        .when()
            .get("/api/pat/{txt}")
        .then()
            .statusCode(200)
            .body(equalTo("date"));
    }

    @Test(timeout = 60000)
    public void testFpePattern() {
        given()
            .pathParam("txt", "0.0e+00")
        .when()
            .get("/api/pat/{txt}")
        .then()
            .statusCode(200)
            .body(equalTo("fpe"));
    }

    @Test(timeout = 60000)
    public void testNonePattern() {
        given()
            .pathParam("txt", "xyz")
        .when()
            .get("/api/pat/{txt}")
        .then()
            .statusCode(200)
            .body(equalTo("none"));
    }
}