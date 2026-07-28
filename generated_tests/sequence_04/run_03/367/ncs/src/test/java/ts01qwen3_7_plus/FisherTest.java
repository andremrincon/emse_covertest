package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class FisherTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testFisherWithOddMAndOddN() {
        given()
            .when()
                .get("/api/fisher/1/1/0.75")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherWithEvenMAndEvenN() {
        given()
            .when()
                .get("/api/fisher/2/2/0.75")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherWithInvalidMParameter() {
        given()
            .when()
                .get("/api/fisher/abc/5/0.75")
            .then()
                .statusCode(400);
    }
}