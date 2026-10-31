package ts01qwen3_7_plus_3;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class PatTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost";
            RestAssured.port = 8080;
        }
    }

    @Test(timeout = 60000)
    public void testInitCoverage() {
        given()
            .when()
                .get("/api/pat/test/ab")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseCoverage() {
        given()
            .when()
                .get("/api/pat/ABABCABAB/ABAB")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectLine63Coverage() {
        given()
            .when()
                .get("/api/pat/ABCCBA/ABC")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectLine84Coverage() {
        given()
            .when()
                .get("/api/pat/CBAABC/ABC")
            .then()
                .statusCode(200);
    }
}