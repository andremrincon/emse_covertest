package ts01glm_5_2_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.nullValue;

public class RemainderTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testRemainderAEqualsZero() {
        given()
            .when()
                .get("/api/remainder/0/5")
            .then()
                .statusCode(200)
                .body("result", nullValue());
    }

    @Test(timeout = 60000)
    public void testRemainderBEqualsZero() {
        given()
            .when()
                .get("/api/remainder/5/0")
            .then()
                .statusCode(200)
                .body("result", nullValue());
    }

    @Test(timeout = 60000)
    public void testRemainderPositivePositive() {
        given()
            .when()
                .get("/api/remainder/17/5")
            .then()
                .statusCode(200)
                .body("result", nullValue());
    }

    @Test(timeout = 60000)
    public void testRemainderPositiveNegative() {
        given()
            .when()
                .get("/api/remainder/17/-5")
            .then()
                .statusCode(200)
                .body("result", nullValue());
    }

    @Test(timeout = 60000)
    public void testRemainderNegativePositive() {
        given()
            .when()
                .get("/api/remainder/-9/3")
            .then()
                .statusCode(200)
                .body("result", nullValue());
    }

    @Test(timeout = 60000)
    public void testRemainderNegativeNegative() {
        given()
            .when()
                .get("/api/remainder/-9/-3")
            .then()
                .statusCode(200)
                .body("result", nullValue());
    }
}