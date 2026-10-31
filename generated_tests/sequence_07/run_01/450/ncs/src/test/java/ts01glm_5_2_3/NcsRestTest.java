package ts01glm_5_2_3;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class NcsRestTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void fisherReturns400WhenMExceeds1000() {
        given()
                .when()
                .get("/api/fisher/1001/5/0.75")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void fisherReturns400WhenNExceeds1000() {
        given()
                .when()
                .get("/api/fisher/10/1001/0.75")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void fisherReturns200WithValidParameters() {
        given()
                .when()
                .get("/api/fisher/10/5/0.75")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void remainderReturns400WhenAExceedsLimit() {
        given()
                .when()
                .get("/api/remainder/10001/5")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void remainderReturns400WhenBExceedsLimit() {
        given()
                .when()
                .get("/api/remainder/17/10001")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void remainderReturns200WithValidParameters() {
        given()
                .when()
                .get("/api/remainder/17/5")
                .then()
                .statusCode(200);
    }
}