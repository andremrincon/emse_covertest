package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

public class PatTest {

    @Before
    public void setUp() {
        String envUrl = System.getenv("BASE_URL");
        RestAssured.baseURI = (envUrl != null && !envUrl.isEmpty()) ? envUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testPatLenLessThanOrEqualTo2() {
        RestAssured.given()
            .when()
            .get("/api/pat/{txt}/{pat}", "a", "a")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundNoPatrev() {
        RestAssured.given()
            .when()
            .get("/api/pat/{txt}/{pat}", "abcXYZ", "abc")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatrevFoundNoPat() {
        RestAssured.given()
            .when()
            .get("/api/pat/{txt}/{pat}", "cbaXYZ", "abc")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatAndPatrevAdjacent() {
        RestAssured.given()
            .when()
            .get("/api/pat/{txt}/{pat}", "abccba", "abc")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatAndPatrevNotAdjacent() {
        RestAssured.given()
            .when()
            .get("/api/pat/{txt}/{pat}", "abcXcba", "abc")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatrevAndPatAdjacent() {
        RestAssured.given()
            .when()
            .get("/api/pat/{txt}/{pat}", "cbaabc", "abc")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatrevAndPatNotAdjacent() {
        RestAssured.given()
            .when()
            .get("/api/pat/{txt}/{pat}", "cbaXabc", "abc")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatPrefixMatchNoFullMatch() {
        RestAssured.given()
            .when()
            .get("/api/pat/{txt}/{pat}", "abX", "abc")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatrevPrefixMatchNoFullMatch() {
        RestAssured.given()
            .when()
            .get("/api/pat/{txt}/{pat}", "cbX", "abc")
            .then()
            .statusCode(200);
    }
}