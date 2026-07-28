package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Ordered4Test {

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = System.getProperty("base.url", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testIncreasingOrder() {
        given()
            .when()
                .get("/api/ordered4/aaaaa/bbbbb/ddddd/ccccc")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDecreasingOrder() {
        given()
            .when()
                .get("/api/ordered4/dddddd/cccccc/aaaaaa/bbbbbb")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedWithinRange() {
        given()
            .when()
                .get("/api/ordered4/aaaaa/bbbbb/ccccc/ddddd")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testYTooShort() {
        given()
            .when()
                .get("/api/ordered4/aaaaa/bbbbb/ccccc/abcd")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testZTooLong() {
        given()
            .when()
                .get("/api/ordered4/aaaaa/bbbbb/ccccccc/ddddd")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testZTooShort() {
        given()
            .when()
                .get("/api/ordered4/aaaaa/bbbbb/abcd/ddddd")
            .then()
                .statusCode(200);
    }
}