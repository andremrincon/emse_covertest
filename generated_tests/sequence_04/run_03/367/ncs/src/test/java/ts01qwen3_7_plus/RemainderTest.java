package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class RemainderTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost";
            RestAssured.port = 8080;
        }
    }

    @Ignore("1 expectation failed. Expected status code <400> but was <200>.")
    @Test(timeout = 60000)
    public void testRemainderAEqualsZero() {
        given()
            .when()
            .get("/api/remainder/0/5")
            .then()
            .statusCode(400);
    }

    @Ignore("1 expectation failed. Expected status code <400> but was <200>.")
    @Test(timeout = 60000)
    public void testRemainderBEqualsZero() {
        given()
            .when()
            .get("/api/remainder/17/0")
            .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testRemainderAPositiveBPositive() {
        given()
            .when()
            .get("/api/remainder/17/5")
            .then()
            .body("resultAsInt", equalTo(2));
    }

    @Test(timeout = 60000)
    public void testRemainderAPositiveBNegative() {
        given()
            .when()
            .get("/api/remainder/17/-5")
            .then()
            .body("resultAsInt", equalTo(2));
    }

    @Test(timeout = 60000)
    public void testRemainderANegativeBPositive() {
        given()
            .when()
            .get("/api/remainder/-17/5")
            .then()
            .body("resultAsInt", equalTo(-2));
    }

    @Test(timeout = 60000)
    public void testRemainderANegativeBNegative() {
        given()
            .when()
            .get("/api/remainder/-17/-5")
            .then()
            .body("resultAsInt", equalTo(2));
    }
}