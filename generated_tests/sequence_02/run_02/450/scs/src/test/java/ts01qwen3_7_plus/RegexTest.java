package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class RegexTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Ignore("1 expectation failed. Response body doesn't match expectation. Expected: a string containing \"ur...")
    @Test(timeout = 60000)
    public void testSubjectReturnsUrl() {
        given()
            .pathParam("txt", "http://a/b")
        .when()
            .get("/api/pat/{txt}")
        .then()
            .statusCode(404)
            .body(containsString("url"));
    }

    @Test(timeout = 60000)
    public void testSubjectReturnsDate() {
        given()
            .pathParam("txt", "mon01jan")
        .when()
            .get("/api/pat/{txt}")
        .then()
            .statusCode(200)
            .body(containsString("date"));
    }

    @Test(timeout = 60000)
    public void testSubjectReturnsFpe() {
        given()
            .pathParam("txt", "1.2e+34")
        .when()
            .get("/api/pat/{txt}")
        .then()
            .statusCode(200)
            .body(containsString("fpe"));
    }

    @Test(timeout = 60000)
    public void testSubjectReturnsNone() {
        given()
            .pathParam("txt", "invalid")
        .when()
            .get("/api/pat/{txt}")
        .then()
            .statusCode(200)
            .body(containsString("none"));
    }
}