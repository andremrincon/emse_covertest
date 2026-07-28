package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FisherTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUri", "http://localhost");
        RestAssured.port = Integer.parseInt(System.getProperty("port", "8080"));
    }

    @Test(timeout = 60000)
    public void testFisherOddOddNormalCase() {
        given()
            .accept("application/json")
        .when()
            .get("/api/fisher/1/1/0.75")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherOddEvenWithLoops() {
        given()
            .accept("application/json")
        .when()
            .get("/api/fisher/3/4/0.75")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherEvenOddElseBranch() {
        given()
            .accept("application/json")
        .when()
            .get("/api/fisher/2/3/0.75")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherEvenEvenWithMainLoop() {
        given()
            .accept("application/json")
        .when()
            .get("/api/fisher/4/2/0.75")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherProbabilityGreaterThanOne() {
        given()
            .accept("application/json")
        .when()
            .get("/api/fisher/1/5/100000")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherProbabilityLessThanZero() {
        given()
            .accept("application/json")
        .when()
            .get("/api/fisher/100/2/0.001")
        .then()
            .statusCode(200);
    }
}