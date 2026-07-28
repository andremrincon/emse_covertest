package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class PatTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        RestAssured.baseURI = (baseUrl != null && !baseUrl.isEmpty()) ? baseUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testPatLenLessThanOrEqualTo2() {
        given()
            .pathParam("txt", "a")
            .pathParam("pat", "a")
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundNoPatRev() {
        given()
            .pathParam("txt", "abc")
            .pathParam("pat", "abc")
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatRevFoundNoPat() {
        given()
            .pathParam("txt", "cba")
            .pathParam("pat", "abc")
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundPatRevImmediatelyAfter() {
        given()
            .pathParam("txt", "abccba")
            .pathParam("pat", "abc")
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundPatRevLater() {
        given()
            .pathParam("txt", "abcXcba")
            .pathParam("pat", "abc")
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatRevFoundPatImmediatelyAfter() {
        given()
            .pathParam("txt", "cbaabc")
            .pathParam("pat", "abc")
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200);
    }
}