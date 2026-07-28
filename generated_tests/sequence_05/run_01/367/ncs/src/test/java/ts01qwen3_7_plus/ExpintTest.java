package ts01qwen3_7_plus;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ExpintTest {

    private final String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";

    @Test(timeout = 60000)
    public void testExpintXGreaterThan1() {
        Response response = given()
            .baseUri(baseUrl)
            .pathParam("n", 3)
            .pathParam("x", 2.5)
        .when()
            .get("/api/expint/{n}/{x}");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintXLessThanOrEqualTo1_NEquals2() {
        Response response = given()
            .baseUri(baseUrl)
            .pathParam("n", 2)
            .pathParam("x", 0.5)
        .when()
            .get("/api/expint/{n}/{x}");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintXLessThanOrEqualTo1_NEquals1() {
        Response response = given()
            .baseUri(baseUrl)
            .pathParam("n", 1)
            .pathParam("x", 0.5)
        .when()
            .get("/api/expint/{n}/{x}");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintXEquals0_NGreaterThan1() {
        Response response = given()
            .baseUri(baseUrl)
            .pathParam("n", 2)
            .pathParam("x", 0.0)
        .when()
            .get("/api/expint/{n}/{x}");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintNLessThan0() {
        Response response = given()
            .baseUri(baseUrl)
            .pathParam("n", -1)
            .pathParam("x", 2.5)
        .when()
            .get("/api/expint/{n}/{x}");

        response.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpintXLessThan0() {
        Response response = given()
            .baseUri(baseUrl)
            .pathParam("n", 3)
            .pathParam("x", -2.5)
        .when()
            .get("/api/expint/{n}/{x}");

        response.then().statusCode(400);
    }
}