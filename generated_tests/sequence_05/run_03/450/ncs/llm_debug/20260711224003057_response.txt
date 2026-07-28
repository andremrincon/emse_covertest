package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FisherTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testFisherBothOdd() {
        given()
            .when()
                .get("/api/fisher/1/1/0.75")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testFisherMOddNEven() {
        given()
            .when()
                .get("/api/fisher/1/2/0.75")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testFisherMEvenNOdd() {
        given()
            .when()
                .get("/api/fisher/2/1/0.75")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testFisherBothEven() {
        given()
            .when()
                .get("/api/fisher/2/2/0.75")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testFisherClampedToZero() {
        given()
            .when()
                .get("/api/fisher/10/2/0.001")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testFisherClampedToOne() {
        given()
            .when()
                .get("/api/fisher/1/1001/0.99")
            .then()
                .statusCode(400);
    }
}