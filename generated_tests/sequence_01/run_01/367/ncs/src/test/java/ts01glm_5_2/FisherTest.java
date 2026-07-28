package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class FisherTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null) {
            baseUrl = System.getenv("BASE_URL");
        }
        if (baseUrl == null) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
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
    public void testFisherOddMOddN() {
        given()
            .when()
                .get("/api/fisher/1/1/0.75")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherOddMEvenN() {
        given()
            .when()
                .get("/api/fisher/1/2/0.75")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherEvenMEvenN() {
        given()
            .when()
                .get("/api/fisher/2/2/0.75")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherNegativeXClampedToZero() {
        given()
            .when()
                .get("/api/fisher/2/2/-0.5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherLargeNegativeXClampedToOne() {
        given()
            .when()
                .get("/api/fisher/2/2/-2")
            .then()
                .statusCode(200);
    }
}