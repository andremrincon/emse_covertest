package ts01qwen3_7_plus_3;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ExpintTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testExpintInvalidN() {
        given().
            pathParam("n", -1).
            pathParam("x", 2.5).
        when().
            get("/api/expint/{n}/{x}").
        then().
            statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpintInvalidX() {
        given().
            pathParam("n", 3).
            pathParam("x", -999.9).
        when().
            get("/api/expint/{n}/{x}").
        then().
            statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpintXGreaterThan1() {
        given().
            pathParam("n", 3).
            pathParam("x", 2.5).
        when().
            get("/api/expint/{n}/{x}").
        then().
            statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintN1XLessThan1() {
        given().
            pathParam("n", 1).
            pathParam("x", 0.1).
        when().
            get("/api/expint/{n}/{x}").
        then().
            statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintN3XLessThan1() {
        given().
            pathParam("n", 3).
            pathParam("x", 0.1).
        when().
            get("/api/expint/{n}/{x}").
        then().
            statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintX0() {
        given().
            pathParam("n", 3).
            pathParam("x", 0.0).
        when().
            get("/api/expint/{n}/{x}").
        then().
            statusCode(200);
    }
}