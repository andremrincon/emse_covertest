package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class PatTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        RestAssured.baseURI = baseUrl != null ? baseUrl : "http://localhost:8080";
    }

    private String enc(String s) {
        return s == null ? null : s.replace(" ", "%20");
    }

    @Test(timeout = 60000)
    public void testPatFoundOnly() {
        String txt = "world hello";
        String pat = "world";
        given()
            .when()
                .get("/api/pat/{txt}/{pat}", enc(txt), pat)
            .then()
                .statusCode(200)
                .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testPatRevFoundOnly() {
        String txt = "dlrow hello";
        String pat = "world";
        given()
            .when()
                .get("/api/pat/{txt}/{pat}", enc(txt), pat)
            .then()
                .statusCode(200)
                .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testPatAndPatRevFoundNotAdjacent() {
        String txt = "world hello dlrow";
        String pat = "world";
        given()
            .when()
                .get("/api/pat/{txt}/{pat}", enc(txt), pat)
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatFollowedByPatRevAdjacent() {
        String txt = "worlddlrow";
        String pat = "world";
        given()
            .when()
                .get("/api/pat/{txt}/{pat}", enc(txt), pat)
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatRevFollowedByPatAdjacent() {
        String txt = "dlrowworld";
        String pat = "world";
        given()
            .when()
                .get("/api/pat/{txt}/{pat}", enc(txt), pat)
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testNeitherFound() {
        String txt = "hello";
        String pat = "xyz";
        given()
            .when()
                .get("/api/pat/{txt}/{pat}", enc(txt), pat)
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatLengthLessThanThree() {
        String txt = "hello";
        String pat = "ab";
        given()
            .when()
                .get("/api/pat/{txt}/{pat}", enc(txt), pat)
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testRegexEndpoint() {
        String txt = "The quick brown fox";
        given()
            .when()
                .get("/api/pat/{txt}", enc(txt))
            .then()
                .statusCode(lessThan(300));
    }
}