package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class PatTest {

    private static final String BASE_URL = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";

    @Test(timeout = 60000)
    public void testPatLenLessThan3() {
        given()
            .pathParam("txt", "a")
            .pathParam("pat", "a")
        .when()
            .get(BASE_URL + "/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatLenGreaterThan3NoMatch() {
        given()
            .pathParam("txt", "ab")
            .pathParam("pat", "abc")
        .when()
            .get(BASE_URL + "/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatMatchButNotEqual() {
        given()
            .pathParam("txt", "abxxc")
            .pathParam("pat", "abc")
        .when()
            .get(BASE_URL + "/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundNoPatRev() {
        given()
            .pathParam("txt", "abcxyz")
            .pathParam("pat", "abc")
        .when()
            .get(BASE_URL + "/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatAndPatRevAdjacent() {
        given()
            .pathParam("txt", "abccba")
            .pathParam("pat", "abc")
        .when()
            .get(BASE_URL + "/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatAndPatRevNotAdjacent() {
        given()
            .pathParam("txt", "abcxxcba")
            .pathParam("pat", "abc")
        .when()
            .get(BASE_URL + "/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatRevFoundNoPat() {
        given()
            .pathParam("txt", "cbaxyz")
            .pathParam("pat", "abc")
        .when()
            .get(BASE_URL + "/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatRevAndPatAdjacent() {
        given()
            .pathParam("txt", "cbaabc")
            .pathParam("pat", "abc")
        .when()
            .get(BASE_URL + "/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatRevAndPatNotAdjacent() {
        given()
            .pathParam("txt", "cbaxxabc")
            .pathParam("pat", "abc")
        .when()
            .get(BASE_URL + "/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200);
    }
}