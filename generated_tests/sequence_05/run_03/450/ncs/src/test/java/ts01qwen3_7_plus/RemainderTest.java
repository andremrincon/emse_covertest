package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class RemainderTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseURI", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testRemainderPositiveAPositiveB() {
        given()
            .pathParam("a", 17)
            .pathParam("b", 5)
        .when()
            .get("/api/remainder/{a}/{b}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderPositiveANegativeB() {
        given()
            .pathParam("a", 17)
            .pathParam("b", -9)
        .when()
            .get("/api/remainder/{a}/{b}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderNegativeAPositiveB() {
        given()
            .pathParam("a", -17)
            .pathParam("b", 5)
        .when()
            .get("/api/remainder/{a}/{b}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderNegativeANegativeB() {
        given()
            .pathParam("a", -17)
            .pathParam("b", -5)
        .when()
            .get("/api/remainder/{a}/{b}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderInvalidA() {
        given()
            .pathParam("a", "abc")
            .pathParam("b", 5)
        .when()
            .get("/api/remainder/{a}/{b}")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testRemainderInvalidB() {
        given()
            .pathParam("a", 17)
            .pathParam("b", "abc")
        .when()
            .get("/api/remainder/{a}/{b}")
        .then()
            .statusCode(400);
    }
}