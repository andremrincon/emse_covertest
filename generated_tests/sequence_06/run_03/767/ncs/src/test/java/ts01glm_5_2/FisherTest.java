package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class FisherTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("BASE_URL");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testFisherBothOdd() {
        given()
            .when()
                .get("/api/fisher/1/1/0.5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherMOddNEvenWithLoop() {
        given()
            .when()
                .get("/api/fisher/1/6/0.5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherMEvenNOdd() {
        given()
            .when()
                .get("/api/fisher/10/5/0.75")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherBothEven() {
        given()
            .when()
                .get("/api/fisher/10/4/0.75")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherClampedToZero() {
        given()
            .when()
                .get("/api/fisher/20/3/0.0001")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherClampedToOne() {
        given()
            .when()
                .get("/api/fisher/1/4/100000000")
            .then()
                .statusCode(200);
    }
}