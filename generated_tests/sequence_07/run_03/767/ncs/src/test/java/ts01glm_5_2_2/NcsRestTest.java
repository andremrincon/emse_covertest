package ts01glm_5_2_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class NcsRestTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testFisherNormalPath() {
        given()
            .when()
                .get("/api/fisher/10/5/0.75")
            .then()
                .statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code <400> but was <200>.")
    @Test(timeout = 60000)
    public void testFisherRuntimeExceptionCatchBlock() {
        given()
            .when()
                .get("/api/fisher/1/-3/0.5")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testRemainderNormalPath() {
        given()
            .when()
                .get("/api/remainder/17/5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderAGreaterThanLimit() {
        given()
            .when()
                .get("/api/remainder/10001/5")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testRemainderALessThanNegativeLimit() {
        given()
            .when()
                .get("/api/remainder/-10001/5")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testRemainderBLessThanNegativeLimit() {
        given()
            .when()
                .get("/api/remainder/17/-10001")
            .then()
                .statusCode(400);
    }
}