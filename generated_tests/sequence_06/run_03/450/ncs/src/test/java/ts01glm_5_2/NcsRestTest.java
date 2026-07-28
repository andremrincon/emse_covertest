package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class NcsRestTest {

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
    public void testBessj_validInput_n3_x2_5() {
        given()
            .when()
                .get("/api/bessj/3/2.5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_xZero() {
        given()
            .when()
                .get("/api/bessj/3/0.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_nTooSmall_returns400() {
        given()
            .when()
                .get("/api/bessj/2/2.5")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFisher_validInput() {
        given()
            .when()
                .get("/api/fisher/10/5/0.75")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_xZero() {
        given()
            .when()
                .get("/api/fisher/1/1/0.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_xGreaterThanOne_returns400() {
        given()
            .when()
                .get("/api/fisher/1/1/1.2")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_xLessThanA() {
        given()
            .when()
                .get("/api/gammq/5.5/2.3")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_xGreaterThanA() {
        given()
            .when()
                .get("/api/gammq/1.0/1000.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_negativeA_returns400() {
        given()
            .when()
                .get("/api/gammq/-1.0/2.0")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testRemainder_validInput() {
        given()
            .when()
                .get("/api/remainder/17/5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainder_negativeDividend() {
        given()
            .when()
                .get("/api/remainder/-9/4")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainder_outOfRange_returns400() {
        given()
            .when()
                .get("/api/remainder/10001/5")
            .then()
                .statusCode(400);
    }
}