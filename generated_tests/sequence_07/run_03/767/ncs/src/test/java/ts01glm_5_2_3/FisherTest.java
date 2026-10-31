package ts01glm_5_2_3;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FisherTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testFisherEvenOddNormalCase() {
        given()
            .when()
                .get("/api/fisher/10/5/0.75")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherOddOddBranch() {
        given()
            .when()
                .get("/api/fisher/1/1/0.75")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherOddOddWithForLoop() {
        given()
            .when()
                .get("/api/fisher/3/5/0.75")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherOddEvenBranch() {
        given()
            .when()
                .get("/api/fisher/1/2/0.75")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherClampToZero() {
        given()
            .when()
                .get("/api/fisher/10/2/0.0001")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherClampToOne() {
        given()
            .when()
                .get("/api/fisher/1/1/1e50")
            .then()
                .statusCode(200);
    }
}