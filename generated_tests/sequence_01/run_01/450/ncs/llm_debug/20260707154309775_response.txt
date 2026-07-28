package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FisherTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        RestAssured.baseURI = (baseUrl != null && !baseUrl.isEmpty()) ? baseUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testFisherOddMOddN() {
        Response response = given()
            .pathParam("m", 1)
            .pathParam("n", 1)
            .pathParam("x", 0.5)
        .when()
            .get("/api/fisher/{m}/{n}/{x}");

        response.then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testFisherOddMEvenN() {
        Response response = given()
            .pathParam("m", 1)
            .pathParam("n", 2)
            .pathParam("x", 0.5)
        .when()
            .get("/api/fisher/{m}/{n}/{x}");

        response.then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testFisherEvenMOddN() {
        Response response = given()
            .pathParam("m", 2)
            .pathParam("n", 1)
            .pathParam("x", 0.5)
        .when()
            .get("/api/fisher/{m}/{n}/{x}");

        response.then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testFisherEvenMEvenN() {
        Response response = given()
            .pathParam("m", 2)
            .pathParam("n", 2)
            .pathParam("x", 0.5)
        .when()
            .get("/api/fisher/{m}/{n}/{x}");

        response.then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testFisherInvalidM() {
        Response response = given()
            .pathParam("m", "abc")
            .pathParam("n", 5)
            .pathParam("x", 0.5)
        .when()
            .get("/api/fisher/{m}/{n}/{x}");

        response.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFisherInvalidN() {
        Response response = given()
            .pathParam("m", 10)
            .pathParam("n", -3)
            .pathParam("x", 0.5)
        .when()
            .get("/api/fisher/{m}/{n}/{x}");

        response.then().statusCode(200);
    }
}