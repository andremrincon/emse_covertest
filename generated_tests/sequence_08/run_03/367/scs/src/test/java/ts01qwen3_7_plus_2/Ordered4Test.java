package ts01qwen3_7_plus_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class Ordered4Test {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testIncreasingOrder() {
        String w = "aaaaa";
        String x = "bbbbb";
        String z = "ddddd";
        String y = "ccccc";

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
        String w = "eeeee";
        String x = "ddddd";
        String z = "bbbbb";
        String y = "ccccc";

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
    public void testUnorderedValidLengths() {
        String w = "aaaaa";
        String x = "bbbbb";
        String z = "bbbbb";
        String y = "aaaaa";

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
    public void testUnorderedInvalidLengthW() {
        String w = "aaa";
        String x = "bbbbb";
        String z = "ddddd";
        String y = "ccccc";

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
    public void testUnorderedInvalidLengthX() {
        String w = "aaaaa";
        String x = "bbbbbbb";
        String z = "ddddd";
        String y = "ccccc";

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
    public void testUnorderedInvalidLengthY() {
        String w = "aaaaa";
        String x = "bbbbb";
        String z = "ddddd";
        String y = "cc";

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