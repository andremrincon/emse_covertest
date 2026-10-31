package ts01glm_5_2_3;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class NcsRestTest {

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testFisherSuccess() {
        given()
            .when()
                .get("/api/fisher/10/5/0.75")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherExceptionCatchBlock() {
        given()
            .when()
                .get("/api/fisher/10/-3/0.75")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderSuccess() {
        given()
            .when()
                .get("/api/remainder/17/5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderAExceedsPositiveLimit() {
        given()
            .when()
                .get("/api/remainder/10001/5")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testRemainderANegativeExceedsLimit() {
        given()
            .when()
                .get("/api/remainder/-10001/5")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testRemainderBNegativeExceedsLimit() {
        given()
            .when()
                .get("/api/remainder/17/-10001")
            .then()
                .statusCode(400);
    }
}