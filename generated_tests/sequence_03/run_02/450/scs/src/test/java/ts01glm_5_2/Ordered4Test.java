package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Ordered4Test {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testIncreasingPathLength5() {
        given()
            .when()
                .get("/api/ordered4/aaaaa/bbbbb/ddddd/ccccc")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDecreasingPathLength5() {
        given()
            .when()
                .get("/api/ordered4/ddddd/ccccc/aaaaa/bbbbb")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedLengthOkNeitherCondition() {
        given()
            .when()
                .get("/api/ordered4/aaaaa/bbbbb/bbbbb/aaaaa")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedLengthTooShort() {
        given()
            .when()
                .get("/api/ordered4/aaaa/bbbb/dddd/cccc")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedLengthTooLong() {
        given()
            .when()
                .get("/api/ordered4/aaaaaaa/bbbbbbb/ddddddd/ccccccc")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIncreasingPathLength6Boundary() {
        given()
            .when()
                .get("/api/ordered4/aaaaaa/bbbbbb/dddddd/cccccc")
            .then()
                .statusCode(200);
    }
}