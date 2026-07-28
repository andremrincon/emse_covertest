package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class FisherTest {

    static {
        RestAssured.baseURI = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testFisher_a1_b1() {
        Response response = given()
            .pathParam("m", 1)
            .pathParam("n", 1)
            .pathParam("x", 0.5)
        .when()
            .get("/api/fisher/{m}/{n}/{x}");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_a1_bNot1() {
        Response response = given()
            .pathParam("m", 1)
            .pathParam("n", 2)
            .pathParam("x", 0.5)
        .when()
            .get("/api/fisher/{m}/{n}/{x}");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_aNot1_b1() {
        Response response = given()
            .pathParam("m", 2)
            .pathParam("n", 1)
            .pathParam("x", 0.5)
        .when()
            .get("/api/fisher/{m}/{n}/{x}");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_aNot1_bNot1() {
        Response response = given()
            .pathParam("m", 2)
            .pathParam("n", 2)
            .pathParam("x", 0.5)
        .when()
            .get("/api/fisher/{m}/{n}/{x}");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_extremeX() {
        Response response = given()
            .pathParam("m", 10)
            .pathParam("n", 5)
            .pathParam("x", 1000000.0)
        .when()
            .get("/api/fisher/{m}/{n}/{x}");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_invalidParam() {
        Response response = given()
            .pathParam("m", "abc")
            .pathParam("n", 5)
            .pathParam("x", 0.5)
        .when()
            .get("/api/fisher/{m}/{n}/{x}");

        response.then().statusCode(400);
    }
}