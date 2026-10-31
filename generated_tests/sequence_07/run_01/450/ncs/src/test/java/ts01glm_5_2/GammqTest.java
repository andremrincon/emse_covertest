package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class GammqTest {

    private static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:8080");

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test(timeout = 60000)
    public void gammq_gser_normalPath() {
        given()
            .when()
                .get("/api/gammq/{a}/{x}", 5.5, 2.3)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void gammq_gser_xZero() {
        given()
            .when()
                .get("/api/gammq/{a}/{x}", 5.5, 0.0)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void gammq_gcf_path() {
        given()
            .when()
                .get("/api/gammq/{a}/{x}", 5.5, 1000.0)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void gammq_invalidA_negative() {
        given()
            .when()
                .get("/api/gammq/{a}/{x}", -1.0, 2.3)
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void gammq_invalidX_negative() {
        given()
            .when()
                .get("/api/gammq/{a}/{x}", 5.5, -1.0)
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void gammq_nonNumericA() {
        given()
            .when()
                .get("/api/gammq/{a}/{x}", "abc", "2.3")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void gammq_smallA_gcf() {
        given()
            .when()
                .get("/api/gammq/{a}/{x}", 0.001, 3.0)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void gammq_aZero_invalid() {
        given()
            .when()
                .get("/api/gammq/{a}/{x}", 0.0, 2.3)
            .then()
                .statusCode(400);
    }
}