package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class FisherTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testFisherA1B1() {
        Response response = given().when().get("/api/fisher/1/1/0.75");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherA1BNot1() {
        Response response = given().when().get("/api/fisher/1/2/0.75");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherANot1B1() {
        Response response = given().when().get("/api/fisher/2/1/0.75");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherANot1BNot1() {
        Response response = given().when().get("/api/fisher/2/2/0.75");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherPOutOfBounds() {
        Response response = given().when().get("/api/fisher/10/5/100.0");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherInvalidParameter() {
        Response response = given().when().get("/api/fisher/abc/5/0.75");
        response.then().statusCode(400);
    }
}