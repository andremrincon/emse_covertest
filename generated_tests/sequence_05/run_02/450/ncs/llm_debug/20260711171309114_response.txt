package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class RemainderTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testRemainderPositiveDividendPositiveDivisor() {
        given()
            .when()
                .get("/api/remainder/17/5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderPositiveDividendNegativeDivisor() {
        given()
            .when()
                .get("/api/remainder/17/-5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderNegativeDividendPositiveDivisor() {
        given()
            .when()
                .get("/api/remainder/-8/4")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderNegativeDividendNegativeDivisor() {
        given()
            .when()
                .get("/api/remainder/-8/-4")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderZeroDividendReturnsBadRequest() {
        given()
            .when()
                .get("/api/remainder/0/5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderZeroDivisorReturnsBadRequest() {
        given()
            .when()
                .get("/api/remainder/17/0")
            .then()
                .statusCode(200);
    }
}