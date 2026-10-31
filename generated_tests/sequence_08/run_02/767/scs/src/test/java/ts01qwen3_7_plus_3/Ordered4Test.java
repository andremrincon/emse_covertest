package ts01qwen3_7_plus_3;

import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Ordered4Test {

    @Test(timeout = 60000)
    public void testLengthCheckFails() {
        String w = "a";
        String x = "bbbbb";
        String z = "ccccc";
        String y = "ddddd";

        given()
            .pathParam("w", w)
            .pathParam("x", x)
            .pathParam("z", z)
            .pathParam("y", y)
        .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIncreasing() {
        String w = "apple";
        String x = "banana";
        String z = "doggy";
        String y = "cherry";

        given()
            .pathParam("w", w)
            .pathParam("x", x)
            .pathParam("z", z)
            .pathParam("y", y)
        .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDecreasing() {
        String w = "zebra";
        String x = "yacht";
        String z = "xerox";
        String y = "water";

        given()
            .pathParam("w", w)
            .pathParam("x", x)
            .pathParam("z", z)
            .pathParam("y", y)
        .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNeitherIncreasingNorDecreasing() {
        String w = "apple";
        String x = "cherry";
        String z = "doggy";
        String y = "banana";

        given()
            .pathParam("w", w)
            .pathParam("x", x)
            .pathParam("z", z)
            .pathParam("y", y)
        .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .statusCode(200);
    }
}