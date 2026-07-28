package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class FisherTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testFisherBothOddWithLoopIterations() {
        given()
            .when()
                .get("/api/fisher/3/3/0.75")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherEvenMOddNWithZkBranch() {
        given()
            .when()
                .get("/api/fisher/4/3/0.75")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherOddMEvenNWithLoop() {
        given()
            .when()
                .get("/api/fisher/3/4/0.75")
            .then()
                .statusCode(200);
    }
}