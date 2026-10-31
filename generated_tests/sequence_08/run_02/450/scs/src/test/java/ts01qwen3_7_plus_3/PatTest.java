package ts01qwen3_7_plus_3;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PatTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testPatLenLessThanOrEqualToTwo() {
        given()
            .when()
            .get("/api/pat/{txt}/{pat}", "hello", "he")
            .then()
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatFoundNoPatRev() {
        given()
            .when()
            .get("/api/pat/{txt}/{pat}", "XabcY", "abc")
            .then()
            .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testPatRevFoundNoPat() {
        given()
            .when()
            .get("/api/pat/{txt}/{pat}", "XcbaY", "abc")
            .then()
            .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testPatThenPatRevAdjacent() {
        given()
            .when()
            .get("/api/pat/{txt}/{pat}", "abccba", "abc")
            .then()
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatRevThenPatAdjacent() {
        given()
            .when()
            .get("/api/pat/{txt}/{pat}", "cbaabc", "abc")
            .then()
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatThenPatRevSeparated() {
        given()
            .when()
            .get("/api/pat/{txt}/{pat}", "abcXcba", "abc")
            .then()
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatRevThenPatSeparated() {
        given()
            .when()
            .get("/api/pat/{txt}/{pat}", "cbaXabc", "abc")
            .then()
            .body(equalTo("0"));
    }
}