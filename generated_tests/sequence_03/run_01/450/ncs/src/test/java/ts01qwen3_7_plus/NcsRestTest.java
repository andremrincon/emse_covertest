package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class NcsRestTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testFisherWithMGreaterThan1000() {
        Response response = given()
            .when()
                .get("/api/fisher/1001/5/0.75");

        response.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFisherWithNGreaterThan1000() {
        Response response = given()
            .when()
                .get("/api/fisher/10/1001/0.75");

        response.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFisherWithValidParameters() {
        Response response = given()
            .when()
                .get("/api/fisher/10/5/0.75");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderWithAGreaterThan10000() {
        Response response = given()
            .when()
                .get("/api/remainder/10001/5");

        response.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testRemainderWithBGreaterThan10000() {
        Response response = given()
            .when()
                .get("/api/remainder/17/10001");

        response.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testRemainderWithValidParameters() {
        Response response = given()
            .when()
                .get("/api/remainder/17/5");

        response.then().statusCode(200);
    }
}