package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

public class ExpintTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getProperty("base.url", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testExpintNegativeN() {
        int n = -1;
        double x = 2.5;

        Response response = RestAssured.given()
                .pathParam("n", n)
                .pathParam("x", x)
                .when()
                .get("/api/expint/{n}/{x}");

        response.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpintNegativeX() {
        int n = 3;
        double x = -999.9;

        Response response = RestAssured.given()
                .pathParam("n", n)
                .pathParam("x", x)
                .when()
                .get("/api/expint/{n}/{x}");

        response.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpintZeroXZeroN() {
        int n = 0;
        double x = 0.0;

        Response response = RestAssured.given()
                .pathParam("n", n)
                .pathParam("x", x)
                .when()
                .get("/api/expint/{n}/{x}");

        response.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpintZeroXOneN() {
        int n = 1;
        double x = 0.0;

        Response response = RestAssured.given()
                .pathParam("n", n)
                .pathParam("x", x)
                .when()
                .get("/api/expint/{n}/{x}");

        response.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpintXGreaterThanOne() {
        int n = 3;
        double x = 2.5;

        Response response = RestAssured.given()
                .pathParam("n", n)
                .pathParam("x", x)
                .when()
                .get("/api/expint/{n}/{x}");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintXLessThanOrEqualToOne() {
        int n = 3;
        double x = 0.1;

        Response response = RestAssured.given()
                .pathParam("n", n)
                .pathParam("x", x)
                .when()
                .get("/api/expint/{n}/{x}");

        response.then().statusCode(200);
    }
}