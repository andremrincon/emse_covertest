package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class PatTest {

    private static final String BASE_URL = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";

    @Test(timeout = 60000)
    public void testPatLenLessThanOrEqualTo2() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/pat/ABAB/AB")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundReverseNotFound() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/pat/ABABXYZW/ABAB")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseFoundPatNotFound() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/pat/BABAXYZW/ABAB")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundReverseFoundNotAdjacent() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/pat/ABABXYBABA/ABAB")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseFoundPatFoundNotAdjacent() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/pat/BABAXYABAB/ABAB")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPalindromePatPlusReverse() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/pat/ABABBABA/ABAB")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPalindromeReversePlusPat() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/pat/BABAABAB/ABAB")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPartialMatchPatReverse() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/pat/BAXYABAB/ABAB")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testJLoopNotExecuted() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/pat/ABABAB/ABAB")
        .then()
            .statusCode(200);
    }
}