package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class BessjTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testBessjWithNLessThan2ThrowsException() {
        int n = 1;
        double x = 2.5;

        Response response = given()
                .pathParam("n", n)
                .pathParam("x", x)
                .when()
                .get("/api/bessj/{n}/{x}");

        response.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessjWithNegativeXAndLargeAbsoluteValue() {
        int n = 3;
        double x = -10.0;

        Response response = given()
                .pathParam("n", n)
                .pathParam("x", x)
                .when()
                .get("/api/bessj/{n}/{x}");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjWithZeroX() {
        int n = 3;
        double x = 0.0;

        Response response = given()
                .pathParam("n", n)
                .pathParam("x", x)
                .when()
                .get("/api/bessj/{n}/{x}");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjWithXGreaterThanN() {
        int n = 3;
        double x = 5.0;

        Response response = given()
                .pathParam("n", n)
                .pathParam("x", x)
                .when()
                .get("/api/bessj/{n}/{x}");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjWithXLessThanOrEqualToN() {
        int n = 5;
        double x = 2.5;

        Response response = given()
                .pathParam("n", n)
                .pathParam("x", x)
                .when()
                .get("/api/bessj/{n}/{x}");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjWithInvalidParameterType() {
        String n = "abc";
        double x = 2.5;

        Response response = given()
                .pathParam("n", n)
                .pathParam("x", x)
                .when()
                .get("/api/bessj/{n}/{x}");

        response.then().statusCode(400);
    }
}