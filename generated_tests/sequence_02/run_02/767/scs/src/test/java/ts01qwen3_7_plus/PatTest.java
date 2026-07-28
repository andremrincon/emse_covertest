package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class PatTest {

    @Before
    public void setUp() {
        String envUrl = System.getenv("BASE_URL");
        RestAssured.baseURI = (envUrl != null && !envUrl.isEmpty()) ? envUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testPatLengthLessThanOrEqualToTwo() {
        given()
            .pathParam("txt", "abc")
            .pathParam("pat", "ab")
            .when()
                .get("/api/pat/{txt}/{pat}")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTxtLengthLessThanPatLength() {
        given()
            .pathParam("txt", "ab")
            .pathParam("pat", "abc")
            .when()
                .get("/api/pat/{txt}/{pat}")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFirstCharMatchesButPatternDoesNot() {
        given()
            .pathParam("txt", "axcde")
            .pathParam("pat", "abc")
            .when()
                .get("/api/pat/{txt}/{pat}")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundReverseNotFound() {
        given()
            .pathParam("txt", "abcde")
            .pathParam("pat", "abc")
            .when()
                .get("/api/pat/{txt}/{pat}")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundReverseFoundImmediatelyAfter() {
        given()
            .pathParam("txt", "abccba")
            .pathParam("pat", "abc")
            .when()
                .get("/api/pat/{txt}/{pat}")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundReverseFoundLater() {
        given()
            .pathParam("txt", "abcxxcba")
            .pathParam("pat", "abc")
            .when()
                .get("/api/pat/{txt}/{pat}")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseFirstCharMatchesButPatternDoesNot() {
        given()
            .pathParam("txt", "cxaxxx")
            .pathParam("pat", "abc")
            .when()
                .get("/api/pat/{txt}/{pat}")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseFoundPatFoundImmediatelyAfter() {
        given()
            .pathParam("txt", "cbaabc")
            .pathParam("pat", "abc")
            .when()
                .get("/api/pat/{txt}/{pat}")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseFoundPatFoundLater() {
        given()
            .pathParam("txt", "cbaxxxabc")
            .pathParam("pat", "abc")
            .when()
                .get("/api/pat/{txt}/{pat}")
            .then()
                .statusCode(200);
    }
}