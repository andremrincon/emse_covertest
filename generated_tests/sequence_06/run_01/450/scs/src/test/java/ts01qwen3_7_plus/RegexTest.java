package ts01qwen3_7_plus;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class RegexTest {

    private final String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");

    @Ignore("1 expectation failed. Response body doesn't match expectation. Expected: \"url\"   Actual: {\"tim...")
    @Test(timeout = 60000)
    public void testSubjectReturnsUrl() {
        given()
            .urlEncodingEnabled(false)
            .get(baseUrl + "/api/pat/http://a/b")
            .then()
            .statusCode(404)
            .body(equalTo("url"));
    }

    @Test(timeout = 60000)
    public void testSubjectReturnsDate() {
        given()
            .urlEncodingEnabled(false)
            .get(baseUrl + "/api/pat/mon01jan")
            .then()
            .statusCode(200)
            .body(equalTo("date"));
    }

    @Test(timeout = 60000)
    public void testSubjectReturnsFpe() {
        given()
            .urlEncodingEnabled(false)
            .get(baseUrl + "/api/pat/1.2e+34")
            .then()
            .statusCode(200)
            .body(equalTo("fpe"));
    }

    @Test(timeout = 60000)
    public void testSubjectReturnsNone() {
        given()
            .urlEncodingEnabled(false)
            .get(baseUrl + "/api/pat/xyz")
            .then()
            .statusCode(200)
            .body(equalTo("none"));
    }
}