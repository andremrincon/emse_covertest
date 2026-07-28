package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class RemainderTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testRemainderPositiveAPositiveB() {
        given()
            .pathParam("a", 10)
            .pathParam("b", 3)
        .when()
            .get("/api/remainder/{a}/{b}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderPositiveANegativeB() {
        given()
            .pathParam("a", 10)
            .pathParam("b", -3)
        .when()
            .get("/api/remainder/{a}/{b}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderNegativeAPositiveB() {
        given()
            .pathParam("a", -10)
            .pathParam("b", 3)
        .when()
            .get("/api/remainder/{a}/{b}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderNegativeANegativeB() {
        given()
            .pathParam("a", -10)
            .pathParam("b", -3)
        .when()
            .get("/api/remainder/{a}/{b}")
        .then()
            .statusCode(200);
    }
}