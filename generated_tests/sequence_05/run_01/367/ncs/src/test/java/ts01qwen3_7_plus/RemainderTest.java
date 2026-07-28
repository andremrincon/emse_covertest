package ts01qwen3_7_plus;

import org.junit.Test;

import static io.restassured.RestAssured.given;

public class RemainderTest {

    @Test(timeout = 60000)
    public void testRemainderPositiveAPositiveB() {
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
                .get("/api/remainder/17/-9")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderNegativeAPositiveB() {
        given()
                .when()
                .get("/api/remainder/-17/5")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderNegativeANegativeB() {
        given()
                .when()
                .get("/api/remainder/-17/-5")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderInvalidA() {
        given()
                .when()
                .get("/api/remainder/abc/5")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testRemainderInvalidB() {
        given()
                .when()
                .get("/api/remainder/17/abc")
                .then()
                .statusCode(400);
    }
}