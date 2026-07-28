package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class RemainderTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testRemainderAZero() {
        given()
            .when()
                .get("/api/remainder/0/5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderBZero() {
        given()
            .when()
                .get("/api/remainder/5/0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderBothPositive() {
        given()
            .when()
                .get("/api/remainder/17/5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderPositiveANegativeB() {
        given()
            .when()
                .get("/api/remainder/17/-5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderNegativeAPositiveB() {
        given()
            .when()
                .get("/api/remainder/-6/3")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderBothNegative() {
        given()
            .when()
                .get("/api/remainder/-6/-3")
            .then()
                .statusCode(200);
    }
}