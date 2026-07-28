package ts01qwen3_7_plus;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class RegexTest {

    private String getBaseUrl() {
        return System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Ignore("1 expectation failed. Response body doesn't match expectation. Expected: \"url\"   Actual: {\"tim...")
    @Test(timeout = 60000)
    public void testSubjectReturnsUrl() {
        given()
            .baseUri(getBaseUrl())
            .pathParam("txt", "http://a/b")
        .when()
            .get("/api/pat/{txt}")
        .then()
            .statusCode(404)
            .body(equalTo("url"));
    }

    @Test(timeout = 60000)
    public void testSubjectReturnsDate() {
        given()
            .baseUri(getBaseUrl())
            .pathParam("txt", "mon12jan")
        .when()
            .get("/api/pat/{txt}")
        .then()
            .statusCode(200)
            .body(equalTo("date"));
    }

    @Test(timeout = 60000)
    public void testSubjectReturnsFpe() {
        given()
            .baseUri(getBaseUrl())
            .pathParam("txt", "1.2e+34")
        .when()
            .get("/api/pat/{txt}")
        .then()
            .statusCode(200)
            .body(equalTo("fpe"));
    }

    @Test(timeout = 60000)
    public void testSubjectReturnsNone() {
        given()
            .baseUri(getBaseUrl())
            .pathParam("txt", "hello")
        .when()
            .get("/api/pat/{txt}")
        .then()
            .statusCode(200)
            .body(equalTo("none"));
    }
}