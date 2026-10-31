package ts01glm_5_2_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class NcsRestTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testFisherSuccess() {
        given()
        .when()
            .get("/api/fisher/10/5/0.75")
        .then()
            .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testFisherGuardMExceedsLimit() {
        given()
        .when()
            .get("/api/fisher/1001/5/0.75")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFisherCatchBlock() {
        given()
        .when()
            .get("/api/fisher/1/-3/0.75")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderSuccess() {
        given()
        .when()
            .get("/api/remainder/17/5")
        .then()
            .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testRemainderGuardAExceedsLimit() {
        given()
        .when()
            .get("/api/remainder/10001/5")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFisherGuardNExceedsLimit() {
        given()
        .when()
            .get("/api/fisher/5/1001/0.75")
        .then()
            .statusCode(400);
    }
}