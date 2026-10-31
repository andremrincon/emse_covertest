package ts01qwen3_7_plus_2;

import org.junit.Test;

import static io.restassured.RestAssured.given;

public class RemainderTest {

    @Test(timeout = 60000)
    public void testRemainderAPositiveBPositive() {
        given()
            .when()
                .get("/api/remainder/17/5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderAPositiveBNegative() {
        given()
            .when()
                .get("/api/remainder/17/-5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderANegativeBPositive() {
        given()
            .when()
                .get("/api/remainder/-17/5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderANegativeBNegative() {
        given()
            .when()
                .get("/api/remainder/-17/-5")
            .then()
                .statusCode(200);
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
}