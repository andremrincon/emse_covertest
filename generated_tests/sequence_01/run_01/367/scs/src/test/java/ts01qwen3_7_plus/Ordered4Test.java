package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class Ordered4Test {

    @Before
    public void setup() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testIncreasingOrder() {
        String w = "apple";
        String x = "banana";
        String z = "delta";
        String y = "cherry";

        given()
            .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}", w, x, z, y)
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDecreasingOrder() {
        String w = "zebra";
        String x = "yak";
        String z = "vulture";
        String y = "wolf";

        given()
            .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}", w, x, z, y)
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedValidLengths() {
        String w = "apple";
        String x = "banana";
        String z = "cherry";
        String y = "apple";

        given()
            .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}", w, x, z, y)
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedInvalidLengthShort() {
        String w = "app";
        String x = "banana";
        String z = "cherry";
        String y = "delta";

        given()
            .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}", w, x, z, y)
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedInvalidLengthLong() {
        String w = "apple";
        String x = "banana";
        String z = "cherry";
        String y = "deltaxx";

        given()
            .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}", w, x, z, y)
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBoundaryLengths() {
        String w = "aaaaa";
        String x = "bbbbb";
        String z = "ddddd";
        String y = "ccccc";

        given()
            .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}", w, x, z, y)
            .then()
            .statusCode(200);
    }
}