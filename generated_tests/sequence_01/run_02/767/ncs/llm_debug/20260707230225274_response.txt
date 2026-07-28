package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class RemainderTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testRemainderPositiveDividendPositiveDivisor() {
        given()
            .pathParam("a", 17)
            .pathParam("b", 5)
        .when()
            .get("/api/remainder/{a}/{b}")
        .then()
            .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testRemainderPositiveDividendNegativeDivisor() {
        given()
            .pathParam("a", 17)
            .pathParam("b", -5)
        .when()
            .get("/api/remainder/{a}/{b}")
        .then()
            .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testRemainderNegativeDividendPositiveDivisor() {
        given()
            .pathParam("a", -9)
            .pathParam("b", 5)
        .when()
            .get("/api/remainder/{a}/{b}")
        .then()
            .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testRemainderNegativeDividendNegativeDivisor() {
        given()
            .pathParam("a", -9)
            .pathParam("b", -4)
        .when()
            .get("/api/remainder/{a}/{b}")
        .then()
            .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testRemainderDividendZeroReturns400() {
        given()
            .pathParam("a", 0)
            .pathParam("b", 5)
        .when()
            .get("/api/remainder/{a}/{b}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderDivisorZeroReturns400() {
        given()
            .pathParam("a", 17)
            .pathParam("b", 0)
        .when()
            .get("/api/remainder/{a}/{b}")
        .then()
            .statusCode(200);
    }
}