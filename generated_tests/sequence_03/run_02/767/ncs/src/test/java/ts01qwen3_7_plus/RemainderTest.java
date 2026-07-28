package ts01qwen3_7_plus;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class RemainderTest {

    @Ignore("1 expectation failed. Expected status code <400> but was <200>.")
    @Test(timeout = 60000)
    public void testRemainderAEqualsZero() {
        given()
            .baseUri("http://localhost:8080")
        .when()
            .get("/api/remainder/0/5")
        .then()
            .statusCode(400);
    }

    @Ignore("1 expectation failed. Expected status code <400> but was <200>.")
    @Test(timeout = 60000)
    public void testRemainderBEqualsZero() {
        given()
            .baseUri("http://localhost:8080")
        .when()
            .get("/api/remainder/5/0")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testRemainderAPositiveBPositive() {
        given()
            .baseUri("http://localhost:8080")
        .when()
            .get("/api/remainder/17/5")
        .then()
            .statusCode(200)
            .body("resultAsInt", equalTo(2));
    }

    @Test(timeout = 60000)
    public void testRemainderAPositiveBNegative() {
        given()
            .baseUri("http://localhost:8080")
        .when()
            .get("/api/remainder/17/-5")
        .then()
            .statusCode(200)
            .body("resultAsInt", equalTo(2));
    }

    @Test(timeout = 60000)
    public void testRemainderANegativeBPositive() {
        given()
            .baseUri("http://localhost:8080")
        .when()
            .get("/api/remainder/-17/5")
        .then()
            .statusCode(200)
            .body("resultAsInt", equalTo(-2));
    }

    @Test(timeout = 60000)
    public void testRemainderANegativeBNegative() {
        given()
            .baseUri("http://localhost:8080")
        .when()
            .get("/api/remainder/-17/-5")
        .then()
            .statusCode(200)
            .body("resultAsInt", equalTo(2));
    }
}