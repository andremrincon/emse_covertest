package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class FisherTest {

    private static final String BASE_URL = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test(timeout = 60000)
    public void testFisherWithA1B1() {
        Response response = given()
        .when()
            .get("/api/fisher/1/1/0.0");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherWithA1BNot1() {
        Response response = given()
        .when()
            .get("/api/fisher/1/2/0.75");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherWithExtremeValuesForPNegative() {
        Response response = given()
        .when()
            .get("/api/fisher/100/1/0.99");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherWithExtremeValuesForPGreaterThan1() {
        Response response = given()
        .when()
            .get("/api/fisher/1/1000/0.99");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherWithNormalValues() {
        Response response = given()
        .when()
            .get("/api/fisher/10/5/0.75");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherWithInvalidM() {
        Response response = given()
        .when()
            .get("/api/fisher/abc/5/0.75");
        response.then().statusCode(400);
    }
}