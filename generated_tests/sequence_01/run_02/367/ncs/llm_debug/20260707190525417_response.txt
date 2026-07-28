package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class RemainderTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("baseUrl");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void remainder_whenAIsZero_shouldReturnNegativeOne() {
        given()
            .pathParam("a", 0)
            .pathParam("b", 5)
        .when()
            .get("/api/remainder/{a}/{b}")
        .then()
            .statusCode(200)
            .body("resultAsInt", equalTo(-1));
    }

    @Test(timeout = 60000)
    public void remainder_whenBothPositive_shouldReturnCorrectRemainder() {
        given()
            .pathParam("a", 17)
            .pathParam("b", 5)
        .when()
            .get("/api/remainder/{a}/{b}")
        .then()
            .statusCode(200)
            .body("resultAsInt", equalTo(2));
    }

    @Test(timeout = 60000)
    public void remainder_whenAPositiveBNegative_shouldReturnCorrectRemainder() {
        given()
            .pathParam("a", 17)
            .pathParam("b", -5)
        .when()
            .get("/api/remainder/{a}/{b}")
        .then()
            .statusCode(200)
            .body("resultAsInt", equalTo(2));
    }

    @Test(timeout = 60000)
    public void remainder_whenANegativeBPositive_shouldReturnCorrectRemainder() {
        given()
            .pathParam("a", -9)
            .pathParam("b", 5)
        .when()
            .get("/api/remainder/{a}/{b}")
        .then()
            .statusCode(200)
            .body("resultAsInt", equalTo(-4));
    }

    @Test(timeout = 60000)
    public void remainder_whenBothNegative_shouldReturnCorrectRemainder() {
        given()
            .pathParam("a", -9)
            .pathParam("b", -5)
        .when()
            .get("/api/remainder/{a}/{b}")
        .then()
            .statusCode(200)
            .body("resultAsInt", equalTo(4));
    }

    @Test(timeout = 60000)
    public void remainder_whenBIsZero_shouldReturnBadRequest() {
        given()
            .pathParam("a", 5)
            .pathParam("b", 0)
        .when()
            .get("/api/remainder/{a}/{b}")
        .then()
            .statusCode(200);
    }
}