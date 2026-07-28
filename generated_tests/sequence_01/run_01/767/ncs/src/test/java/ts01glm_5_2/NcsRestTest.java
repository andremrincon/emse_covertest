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
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("BASE_URL");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testBessjNormalCase() {
        given()
                .pathParam("n", 3)
                .pathParam("x", 2.5)
        .when()
                .get("/api/bessj/{n}/{x}")
        .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjWithZeroX() {
        given()
                .pathParam("n", 3)
                .pathParam("x", 0.0)
        .when()
                .get("/api/bessj/{n}/{x}")
        .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjInvalidN() {
        given()
                .pathParam("n", 2)
                .pathParam("x", 2.5)
        .when()
                .get("/api/bessj/{n}/{x}")
        .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFisherNormalCase() {
        given()
                .pathParam("m", 10)
                .pathParam("n", 5)
                .pathParam("x", 0.75)
        .when()
                .get("/api/fisher/{m}/{n}/{x}")
        .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherWithZeroX() {
        given()
                .pathParam("m", 10)
                .pathParam("n", 5)
                .pathParam("x", 0.0)
        .when()
                .get("/api/fisher/{m}/{n}/{x}")
        .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherInvalidM() {
        given()
                .pathParam("m", 1001)
                .pathParam("n", 5)
                .pathParam("x", 0.75)
        .when()
                .get("/api/fisher/{m}/{n}/{x}")
        .then()
                .statusCode(400);
    }
}