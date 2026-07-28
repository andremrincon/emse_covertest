package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class RemainderTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testRemainderPositiveDividendPositiveDivisor() {
        given()
            .when()
                .get("/api/remainder/17/5")
            .then()
                .statusCode(lessThan(300))
                .body("resultAsInt", equalTo(2));
    }

    @Test(timeout = 60000)
    public void testRemainderPositiveDividendNegativeDivisor() {
        given()
            .when()
                .get("/api/remainder/17/-5")
            .then()
                .statusCode(lessThan(300))
                .body("resultAsInt", equalTo(2));
    }

    @Test(timeout = 60000)
    public void testRemainderNegativeDividendPositiveDivisor() {
        given()
            .when()
                .get("/api/remainder/-9/5")
            .then()
                .statusCode(lessThan(300))
                .body("resultAsInt", equalTo(-4));
    }

    @Test(timeout = 60000)
    public void testRemainderNegativeDividendNegativeDivisor() {
        given()
            .when()
                .get("/api/remainder/-9/-5")
            .then()
                .statusCode(lessThan(300))
                .body("resultAsInt", equalTo(4));
    }

    @Test(timeout = 60000)
    public void testRemainderDividendZero() {
        given()
            .when()
                .get("/api/remainder/0/5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderDivisorZero() {
        given()
            .when()
                .get("/api/remainder/17/0")
            .then()
                .statusCode(200);
    }
}