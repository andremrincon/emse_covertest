package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class BessjTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testBessjWithNLessThan2() {
        Response response = given()
            .pathParam("n", -5)
            .pathParam("x", 2.5)
        .when()
            .get("/api/bessj/{n}/{x}");

        response.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessjWithAxGreaterThanNAndAxGreaterThanOrEqualTo8() {
        Response response = given()
            .pathParam("n", 3)
            .pathParam("x", 10.0)
        .when()
            .get("/api/bessj/{n}/{x}");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjWithAxGreaterThanNAndAxLessThan8() {
        Response response = given()
            .pathParam("n", 3)
            .pathParam("x", 5.0)
        .when()
            .get("/api/bessj/{n}/{x}");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjWithAxLessThanOrEqualToN() {
        Response response = given()
            .pathParam("n", 3)
            .pathParam("x", 2.0)
        .when()
            .get("/api/bessj/{n}/{x}");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjWithXEqualToZero() {
        Response response = given()
            .pathParam("n", 3)
            .pathParam("x", 0.0)
        .when()
            .get("/api/bessj/{n}/{x}");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjWithNegativeXAndOddN() {
        Response response = given()
            .pathParam("n", 3)
            .pathParam("x", -10.0)
        .when()
            .get("/api/bessj/{n}/{x}");

        response.then().statusCode(200);
    }
}