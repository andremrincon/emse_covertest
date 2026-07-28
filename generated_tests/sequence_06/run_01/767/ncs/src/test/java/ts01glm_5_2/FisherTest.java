package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FisherTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("base.url", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testFisherBothOddParameters() {
        given()
            .when()
                .get("/api/fisher/3/3/0.75")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherMOddNEvenParameters() {
        given()
            .when()
                .get("/api/fisher/3/4/0.75")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherMEvenNOddParameters() {
        given()
            .when()
                .get("/api/fisher/4/3/0.75")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherBothEvenParameters() {
        given()
            .when()
                .get("/api/fisher/4/4/0.75")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherEdgeCaseXZeroLoopsSkipped() {
        given()
            .when()
                .get("/api/fisher/1/1/0.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherInvalidParameterType() {
        given()
            .when()
                .get("/api/fisher/abc/1/0.75")
            .then()
                .statusCode(400);
    }
}