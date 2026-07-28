package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.*;

public class RemainderTest {

    private static String baseUrl;

    @BeforeClass
    public static void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void remainder_aEqualsZero_returnsNegativeOne() {
        RestAssured.given()
            .pathParam("a", 0)
            .pathParam("b", 5)
        .when()
            .get("/api/remainder/{a}/{b}")
        .then()
            .statusCode(200)
            .body("resultAsInt", equalTo(-1));
    }

    @Test(timeout = 60000)
    public void remainder_bothPositive_executesPositivePositiveLoop() {
        RestAssured.given()
            .pathParam("a", 17)
            .pathParam("b", 5)
        .when()
            .get("/api/remainder/{a}/{b}")
        .then()
            .statusCode(200)
            .body("resultAsInt", equalTo(2));
    }

    @Test(timeout = 60000)
    public void remainder_aPositiveBNegative_executesPositiveNegativeLoop() {
        RestAssured.given()
            .pathParam("a", 17)
            .pathParam("b", -5)
        .when()
            .get("/api/remainder/{a}/{b}")
        .then()
            .statusCode(200)
            .body("resultAsInt", equalTo(2));
    }

    @Test(timeout = 60000)
    public void remainder_aNegativeBPositive_executesNegativePositiveLoop() {
        RestAssured.given()
            .pathParam("a", -9)
            .pathParam("b", 5)
        .when()
            .get("/api/remainder/{a}/{b}")
        .then()
            .statusCode(200)
            .body("resultAsInt", equalTo(-4));
    }

    @Test(timeout = 60000)
    public void remainder_bothNegative_executesNegativeNegativeLoop() {
        RestAssured.given()
            .pathParam("a", -9)
            .pathParam("b", -5)
        .when()
            .get("/api/remainder/{a}/{b}")
        .then()
            .statusCode(200)
            .body("resultAsInt", equalTo(4));
    }

    @Test(timeout = 60000)
    public void remainder_bEqualsZero_returnsNegativeOne() {
        RestAssured.given()
            .pathParam("a", 5)
            .pathParam("b", 0)
        .when()
            .get("/api/remainder/{a}/{b}")
        .then()
            .statusCode(200)
            .body("resultAsInt", equalTo(-1));
    }
}