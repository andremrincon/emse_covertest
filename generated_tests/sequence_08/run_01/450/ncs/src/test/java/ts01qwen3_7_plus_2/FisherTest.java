package ts01qwen3_7_plus_2;

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
    public void testFisherP_lessThanZero() {
        given()
            .when()
                .get("/api/fisher/2/2/-0.5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherP_greaterThanOne() {
        given()
            .when()
                .get("/api/fisher/2/2/1000000000000000.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherP_betweenZeroAndOne() {
        given()
            .when()
                .get("/api/fisher/10/5/0.75")
            .then()
                .statusCode(200);
    }
}