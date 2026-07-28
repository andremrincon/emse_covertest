package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FisherTest {

    private static final String BASE_URL = System.getProperty("baseUrl", System.getenv().getOrDefault("BASE_URL", "http://localhost:8080"));

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test(timeout = 60000)
    public void testFisherOddMOddN() {
        given()
            .when()
                .get("/api/fisher/3/3/0.5")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testFisherOddMEvenN() {
        given()
            .when()
                .get("/api/fisher/3/4/0.5")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testFisherEvenMOddN() {
        given()
            .when()
                .get("/api/fisher/4/3/0.5")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testFisherEvenMEvenN() {
        given()
            .when()
                .get("/api/fisher/4/4/0.5")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testFisherLargeMClampsToZero() {
        given()
            .when()
                .get("/api/fisher/1001/1/0.5")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFisherLargeNClampsToOne() {
        given()
            .when()
                .get("/api/fisher/1/1001/0.5")
            .then()
                .statusCode(400);
    }
}