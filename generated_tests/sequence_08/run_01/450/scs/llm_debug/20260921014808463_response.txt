package ts01qwen3_7_plus_2;

import io.restassured.RestAssured;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class PatTest {

    private final String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";

    @Test(timeout = 60000)
    public void testSubjectPatFollowedByPatRev() {
        RestAssured.baseURI = baseUrl;
        given()
            .when()
                .get("/api/pat/abccba/abc")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectPatRevFollowedByPat() {
        RestAssured.baseURI = baseUrl;
        given()
            .when()
                .get("/api/pat/cbaabc/abc")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectPatRevFollowedBySomethingThenPat() {
        RestAssured.baseURI = baseUrl;
        given()
            .when()
                .get("/api/pat/cbaxabc/abc")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectPatLenLessThanOrEqualToTwo() {
        RestAssured.baseURI = baseUrl;
        given()
            .when()
                .get("/api/pat/ab/ab")
            .then()
                .statusCode(200);
    }
}