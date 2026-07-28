package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Ordered4Test {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testIncreasingOrder() {
        String w = "apple";
        String x = "banana";
        String y = "cherry";
        String z = "elder";

        given()
            .pathParam("w", w)
            .pathParam("x", x)
            .pathParam("z", z)
            .pathParam("y", y)
        .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .statusCode(200)
            .body(equalTo("increasing"));
    }

    @Test(timeout = 60000)
    public void testDecreasingOrder() {
        String w = "zebra";
        String x = "yacht";
        String y = "water";
        String z = "vodka";

        given()
            .pathParam("w", w)
            .pathParam("x", x)
            .pathParam("z", z)
            .pathParam("y", y)
        .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .statusCode(200)
            .body(equalTo("decreasing"));
    }

    @Test(timeout = 60000)
    public void testYLengthNotInRange() {
        String w = "apple";
        String x = "banana";
        String y = "ch";
        String z = "elder";

        given()
            .pathParam("w", w)
            .pathParam("x", x)
            .pathParam("z", z)
            .pathParam("y", y)
        .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .statusCode(200)
            .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testZLengthNotInRange() {
        String w = "apple";
        String x = "banana";
        String y = "cherry";
        String z = "el";

        given()
            .pathParam("w", w)
            .pathParam("x", x)
            .pathParam("z", z)
            .pathParam("y", y)
        .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .statusCode(200)
            .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testAllLengthsInRangeButNotOrdered() {
        String w = "apple";
        String x = "banana";
        String y = "cherry";
        String z = "berry";

        given()
            .pathParam("w", w)
            .pathParam("x", x)
            .pathParam("z", z)
            .pathParam("y", y)
        .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .statusCode(200)
            .body(equalTo("unordered"));
    }
}