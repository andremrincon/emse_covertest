package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FisherTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testFisherEvenMOddN() {
        given()
            .when()
                .get("/api/fisher/10/5/0.75")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherOddMOddNNoLoops() {
        given()
            .when()
                .get("/api/fisher/1/1/0.75")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherOddMOddNWithLoops() {
        given()
            .when()
                .get("/api/fisher/5/5/0.75")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherEvenMEvenN() {
        given()
            .when()
                .get("/api/fisher/10/4/0.75")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testFisherOddMEvenN() {
        given()
            .when()
                .get("/api/fisher/5/4/0.75")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testFisherLargeMForNegativeP() {
        given()
            .when()
                .get("/api/fisher/100/1/0.1")
            .then()
                .statusCode(lessThan(300));
    }
}