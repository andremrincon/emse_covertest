package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PatTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testPatFoundReverseFoundNonAdjacent() {
        given()
            .when()
                .get("/api/pat/ABABCABAB/ABAB")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseFoundPatFoundAdjacent() {
        given()
            .when()
                .get("/api/pat/BABAABAB/ABAB")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundNoReverse() {
        given()
            .when()
                .get("/api/pat/ABAB/ABAB")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseFoundNoPat() {
        given()
            .when()
                .get("/api/pat/BABA/ABAB")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNeitherFound() {
        given()
            .when()
                .get("/api/pat/XYZW/ABAB")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatternTooShort() {
        given()
            .when()
                .get("/api/pat/AB/AB")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPalindromePatThenReverse() {
        given()
            .when()
                .get("/api/pat/ABABBABA/ABAB")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseFoundPatFoundNonAdjacent() {
        given()
            .when()
                .get("/api/pat/BABACDABAB/ABAB")
            .then()
                .statusCode(200);
    }
}