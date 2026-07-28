package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class Ordered4Test {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testIncreasingOrderAllValidLengths() {
        String w = "aaaaa";
        String x = "bbbbb";
        String z = "ddddd";
        String y = "ccccc";

        given()
            .when()
                .get("/api/ordered4/{w}/{x}/{z}/{y}", w, x, z, y)
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testDecreasingOrderAllValidLengths() {
        String w = "ddddd";
        String x = "ccccc";
        String z = "aaaaa";
        String y = "bbbbb";

        given()
            .when()
                .get("/api/ordered4/{w}/{x}/{z}/{y}", w, x, z, y)
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testUnorderedAllValidLengths() {
        String w = "aaaaa";
        String x = "bbbbb";
        String z = "ccccc";
        String y = "ddddd";

        given()
            .when()
                .get("/api/ordered4/{w}/{x}/{z}/{y}", w, x, z, y)
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testOuterIfFalseOneStringTooShort() {
        String w = "a";
        String x = "bbbbb";
        String z = "ccccc";
        String y = "ddddd";

        given()
            .when()
                .get("/api/ordered4/{w}/{x}/{z}/{y}", w, x, z, y)
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testOuterIfFalseOneStringTooLong() {
        String w = "aaaaaaa";
        String x = "bbbbb";
        String z = "ccccc";
        String y = "ddddd";

        given()
            .when()
                .get("/api/ordered4/{w}/{x}/{z}/{y}", w, x, z, y)
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testServerErrorWithExcessivelyLongInput() {
        String longY = "yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy";

        given()
            .when()
                .get("/api/ordered4/{w}/{x}/{z}/{y}", "apple", "banana", "cherry", longY)
            .then()
                .statusCode(200);
    }
}