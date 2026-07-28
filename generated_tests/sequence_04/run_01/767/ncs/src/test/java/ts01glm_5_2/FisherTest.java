package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class FisherTest {

    @BeforeClass
    public static void setUp() {
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
    public void testFisherBothOddWithLoops() {
        given()
            .when()
                .get("/api/fisher/3/5/0.5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherBothEvenWithLoops() {
        given()
            .when()
                .get("/api/fisher/4/4/0.5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherOddEvenResultClampedToOne() {
        given()
            .when()
                .get("/api/fisher/11/10/100")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherEvenOddResultClampedToZero() {
        given()
            .when()
                .get("/api/fisher/100/1/0.001")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherBothOddNoLoops() {
        given()
            .when()
                .get("/api/fisher/1/1/0.5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherBothEvenNoLoops() {
        given()
            .when()
                .get("/api/fisher/2/2/0.5")
            .then()
                .statusCode(200);
    }
}