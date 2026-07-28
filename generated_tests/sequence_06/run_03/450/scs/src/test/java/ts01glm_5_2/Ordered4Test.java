package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Ordered4Test {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testIncreasingOrder() {
        given()
            .when()
                .get("/api/ordered4/apple/berry/zebra/mango")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDecreasingOrder() {
        given()
            .when()
                .get("/api/ordered4/zebra/mango/apple/berry")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedValidLengths() {
        given()
            .when()
                .get("/api/ordered4/zebra/apple/cherry/berry")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testStringLengthTooShort() {
        given()
            .when()
                .get("/api/ordered4/a/bb/ccc/dddd")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testStringLengthTooLong() {
        given()
            .when()
                .get("/api/ordered4/abcdefg/berry/zebra/mango")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBothConditionsFailAtFirstCheck() {
        given()
            .when()
                .get("/api/ordered4/apple/zebra/berry/mango")
            .then()
                .statusCode(200);
    }
}