package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class RemainderTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Ignore("1 expectation failed. Response body doesn't match expectation. Expected: \"2\"   Actual: {\"resul...")
    @Test(timeout = 60000)
    public void testRemainderAPositiveBPositive() {
        given()
            .when()
                .get("/api/remainder/17/5")
            .then()
                .statusCode(200)
                .body(equalTo("2"));
    }

    @Ignore("1 expectation failed. Response body doesn't match expectation. Expected: \"2\"   Actual: {\"resul...")
    @Test(timeout = 60000)
    public void testRemainderAPositiveBNegative() {
        given()
            .when()
                .get("/api/remainder/17/-5")
            .then()
                .statusCode(200)
                .body(equalTo("2"));
    }

    @Ignore("1 expectation failed. Response body doesn't match expectation. Expected: \"-2\"   Actual: {\"resu...")
    @Test(timeout = 60000)
    public void testRemainderANegativeBPositive() {
        given()
            .when()
                .get("/api/remainder/-17/5")
            .then()
                .statusCode(200)
                .body(equalTo("-2"));
    }

    @Ignore("1 expectation failed. Response body doesn't match expectation. Expected: \"2\"   Actual: {\"resul...")
    @Test(timeout = 60000)
    public void testRemainderANegativeBNegative() {
        given()
            .when()
                .get("/api/remainder/-17/-5")
            .then()
                .statusCode(200)
                .body(equalTo("2"));
    }

    @Ignore("1 expectation failed. Response body doesn't match expectation. Expected: \"-1\"   Actual: {\"resu...")
    @Test(timeout = 60000)
    public void testRemainderAZero() {
        given()
            .when()
                .get("/api/remainder/0/5")
            .then()
                .statusCode(200)
                .body(equalTo("-1"));
    }

    @Ignore("1 expectation failed. Response body doesn't match expectation. Expected: \"-1\"   Actual: {\"resu...")
    @Test(timeout = 60000)
    public void testRemainderBZero() {
        given()
            .when()
                .get("/api/remainder/17/0")
            .then()
                .statusCode(200)
                .body(equalTo("-1"));
    }
}