package ts01qwen3_7_plus_3;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Ordered4Test {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("test.baseURI", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testIncreasingOrder() {
        given()
            .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}", "apple", "banana", "x-ray", "cherry")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDecreasingOrder() {
        given()
            .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}", "x-ray", "cherry", "apple", "banana")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedWrongLengths() {
        given()
            .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}", "a", "b", "d", "c")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedRightLengthsWrongOrder() {
        given()
            .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}", "apple", "cherry", "x-ray", "banana")
            .then()
            .statusCode(200);
    }
}