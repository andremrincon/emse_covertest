package ts01qwen3_7_plus_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Ordered4Test {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testLengthsLessThan5() {
        given()
            .pathParam("w", "a")
            .pathParam("x", "b")
            .pathParam("z", "c")
            .pathParam("y", "d")
        .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testLengthGreaterThan6() {
        given()
            .pathParam("w", "appleee")
            .pathParam("x", "banana")
            .pathParam("z", "cherry")
            .pathParam("y", "donut")
        .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIncreasingOrder() {
        given()
            .pathParam("w", "apple")
            .pathParam("x", "banana")
            .pathParam("z", "donut")
            .pathParam("y", "cherry")
        .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDecreasingOrder() {
        given()
            .pathParam("w", "donut")
            .pathParam("x", "cherry")
            .pathParam("z", "apple")
            .pathParam("y", "banana")
        .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedValidLengths() {
        given()
            .pathParam("w", "apple")
            .pathParam("x", "cherry")
            .pathParam("z", "banana")
            .pathParam("y", "donut")
        .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testXLengthGreaterThan6() {
        given()
            .pathParam("w", "apple")
            .pathParam("x", "bananaa")
            .pathParam("z", "cherry")
            .pathParam("y", "donut")
        .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .statusCode(200);
    }
}