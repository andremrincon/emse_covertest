package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class PatTest {

    @Test(timeout = 60000)
    public void testPatLengthLessThanOrEqualToTwo() {
        given()
            .when()
            .get("/api/pat/abc/ab")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatternFoundOnly() {
        given()
            .when()
            .get("/api/pat/xyzabcxyz/abc")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseFoundOnly() {
        given()
            .when()
            .get("/api/pat/xyzcbaxyz/abc")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBothFoundAdjacentPatThenReverse() {
        given()
            .when()
            .get("/api/pat/abccba/abc")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBothFoundAdjacentReverseThenPat() {
        given()
            .when()
            .get("/api/pat/cbaabc/abc")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBothFoundNotAdjacent() {
        given()
            .when()
            .get("/api/pat/abcxyzcba/abc")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNeitherFound() {
        given()
            .when()
            .get("/api/pat/xyz/abc")
            .then()
            .statusCode(200);
    }
}