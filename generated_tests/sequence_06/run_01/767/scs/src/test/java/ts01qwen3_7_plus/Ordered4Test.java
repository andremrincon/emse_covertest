package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Ordered4Test {

    @Before
    public void setup() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testIncreasingOrderAllLengthsValid() {
        String w = "apple";
        String x = "banana";
        String z = "delta";
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
    public void testDecreasingOrderAllLengthsValid() {
        String w = "zebra";
        String x = "yacht";
        String z = "wheat";
        String y = "xerox";

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
    public void testUnorderedAllLengthsValidButNotSorted() {
        String w = "apple";
        String x = "apple";
        String z = "apple";
        String y = "apple";

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
    public void testUnorderedWLengthTooShort() {
        String w = "app";
        String x = "banana";
        String z = "delta";
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
    public void testUnorderedWLengthTooLong() {
        String w = "applesauce";
        String x = "banana";
        String z = "delta";
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
    public void testUnorderedXLengthTooShort() {
        String w = "apple";
        String x = "ban";
        String z = "delta";
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
}