package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Ordered4Test {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("baseUrl");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testUnorderedWhenWLengthBelowMinimum() {
        given()
            .when()
                .get("/api/ordered4/abcd/bbbbb/ccccc/ddddd")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedWhenWLengthAboveMaximum() {
        given()
            .when()
                .get("/api/ordered4/abcdefg/bbbbb/ccccc/ddddd")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIncreasingWhenAllLength5AndZGreaterThanYGreaterThanXGreaterThanW() {
        given()
            .when()
                .get("/api/ordered4/aaaaa/bbbbb/ddddd/ccccc")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDecreasingWhenAllLength6AndWGreaterThanXGreaterThanYGreaterThanZ() {
        given()
            .when()
                .get("/api/ordered4/dddddd/cccccc/aaaaaa/bbbbbb")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedWhenAllLength5ButNeitherIncreasingNorDecreasing() {
        given()
            .when()
                .get("/api/ordered4/aaaaa/aaaaa/aaaaa/aaaaa")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIncreasingWhenAllLength6AndZGreaterThanYGreaterThanXGreaterThanW() {
        given()
            .when()
                .get("/api/ordered4/aaaaaa/bbbbbb/dddddd/cccccc")
            .then()
                .statusCode(200);
    }
}