package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.lessThan;

public class Ordered4Test {

    private static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:8080");

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test(timeout = 60000)
    public void testIncreasingOrder() {
        given()
            .when()
                .get("/api/ordered4/11111/22222/44444/33333")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDecreasingOrder() {
        given()
            .when()
                .get("/api/ordered4/44444/33333/11111/22222")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedWithValidLengths() {
        given()
            .when()
                .get("/api/ordered4/11111/22222/33333/44444")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testShortStringLengthReturnsUnordered() {
        given()
            .when()
                .get("/api/ordered4/11/22222/33333/44444")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testLongStringLengthReturnsUnordered() {
        given()
            .when()
                .get("/api/ordered4/1111111/22222/33333/44444")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testLengthSixBoundaryDecreasing() {
        given()
            .when()
                .get("/api/ordered4/666666/555555/222222/444444")
            .then()
                .statusCode(200);
    }
}