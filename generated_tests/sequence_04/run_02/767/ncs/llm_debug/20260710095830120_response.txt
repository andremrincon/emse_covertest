package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class NcsRestTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testFisherValidParameters() {
        Response response = given()
            .pathParam("m", 10)
            .pathParam("n", 5)
            .pathParam("x", 0.75)
        .when()
            .get("/api/fisher/{m}/{n}/{x}");

        response.then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testFisherInvalidM() {
        Response response = given()
            .pathParam("m", 1001)
            .pathParam("n", 5)
            .pathParam("x", 0.75)
        .when()
            .get("/api/fisher/{m}/{n}/{x}");

        assertEquals(400, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFisherRuntimeException() {
        Response response = given()
            .pathParam("m", 10)
            .pathParam("n", 5)
            .pathParam("x", 1.2)
        .when()
            .get("/api/fisher/{m}/{n}/{x}");

        assertEquals(200, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testRemainderValidParameters() {
        Response response = given()
            .pathParam("a", 17)
            .pathParam("b", 5)
        .when()
            .get("/api/remainder/{a}/{b}");

        response.then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testRemainderInvalidA() {
        Response response = given()
            .pathParam("a", 10001)
            .pathParam("b", 5)
        .when()
            .get("/api/remainder/{a}/{b}");

        assertEquals(400, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testRemainderDivisionByZero() {
        Response response = given()
            .pathParam("a", 17)
            .pathParam("b", 0)
        .when()
            .get("/api/remainder/{a}/{b}");

        assertEquals(200, response.getStatusCode());
    }
}