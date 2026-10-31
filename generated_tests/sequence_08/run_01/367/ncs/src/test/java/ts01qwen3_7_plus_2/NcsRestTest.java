package ts01qwen3_7_plus_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class NcsRestTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testBessjSuccess() {
        Response response = given()
            .pathParam("n", 3)
            .pathParam("x", 2.5)
        .when()
            .get("/api/bessj/{n}/{x}");
        assertEquals(200, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testBessjFailureNSmall() {
        Response response = given()
            .pathParam("n", 1)
            .pathParam("x", 2.5)
        .when()
            .get("/api/bessj/{n}/{x}");
        assertEquals(400, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testBessjFailureNLarge() {
        Response response = given()
            .pathParam("n", 1001)
            .pathParam("x", 2.5)
        .when()
            .get("/api/bessj/{n}/{x}");
        assertEquals(400, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFisherSuccess() {
        Response response = given()
            .pathParam("m", 10)
            .pathParam("n", 5)
            .pathParam("x", 0.75)
        .when()
            .get("/api/fisher/{m}/{n}/{x}");
        assertEquals(200, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFisherFailureMLarge() {
        Response response = given()
            .pathParam("m", 1001)
            .pathParam("n", 5)
            .pathParam("x", 0.75)
        .when()
            .get("/api/fisher/{m}/{n}/{x}");
        assertEquals(400, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFisherFailureNLarge() {
        Response response = given()
            .pathParam("m", 10)
            .pathParam("n", 1001)
            .pathParam("x", 0.75)
        .when()
            .get("/api/fisher/{m}/{n}/{x}");
        assertEquals(400, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFisherCatchBlock() {
        Response response = given()
            .pathParam("m", 10)
            .pathParam("n", -3)
            .pathParam("x", 0.5)
        .when()
            .get("/api/fisher/{m}/{n}/{x}");
        assertEquals(200, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testRemainderSuccess() {
        Response response = given()
            .pathParam("a", 17)
            .pathParam("b", 5)
        .when()
            .get("/api/remainder/{a}/{b}");
        assertEquals(200, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testRemainderFailureALarge() {
        Response response = given()
            .pathParam("a", 10001)
            .pathParam("b", 5)
        .when()
            .get("/api/remainder/{a}/{b}");
        assertEquals(400, response.getStatusCode());
    }
}