package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class FisherTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null) {
            baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testFisherBranchA2B1() {
        given()
            .when()
                .get("/api/fisher/10/5/0.75")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherBranchA1B2() {
        given()
            .when()
                .get("/api/fisher/1/2/0.75")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherBranchA2B2() {
        given()
            .when()
                .get("/api/fisher/2/2/0.75")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherBranchA1B1() {
        given()
            .when()
                .get("/api/fisher/1/1/0.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherExtremeX() {
        given()
            .when()
                .get("/api/fisher/10/5/1e-15")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherInvalidParam() {
        given()
            .when()
                .get("/api/fisher/abc/5/0.75")
            .then()
                .statusCode(400);
    }
}