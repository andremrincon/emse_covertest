package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class NcsRestTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("baseUrl");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testBessj_validParams_n3_x2_5() {
        given()
                .when()
                .get("/api/bessj/3/2.5")
                .then()
                .statusCode(200)
                .body("resultAsDouble", notNullValue());
    }

    @Test(timeout = 60000)
    public void testBessj_smallX_n3_x1e10() {
        given()
                .when()
                .get("/api/bessj/3/0.0000000001")
                .then()
                .statusCode(200)
                .body("resultAsDouble", notNullValue());
    }

    @Test(timeout = 60000)
    public void testBessj_largeN_1000() {
        given()
                .when()
                .get("/api/bessj/1000/5.0")
                .then()
                .statusCode(200)
                .body("resultAsDouble", notNullValue());
    }

    @Test(timeout = 60000)
    public void testFisher_validParams_m10_n5_x0_75() {
        given()
                .when()
                .get("/api/fisher/10/5/0.75")
                .then()
                .statusCode(200)
                .body("resultAsDouble", notNullValue());
    }

    @Test(timeout = 60000)
    public void testFisher_xZero_m1_n1_x0() {
        given()
                .when()
                .get("/api/fisher/1/1/0.0")
                .then()
                .statusCode(200)
                .body("resultAsDouble", notNullValue());
    }

    @Test(timeout = 60000)
    public void testFisher_largeM_n_m1000_n1000() {
        given()
                .when()
                .get("/api/fisher/1000/1000/0.5")
                .then()
                .statusCode(200)
                .body("resultAsDouble", notNullValue());
    }

    @Test(timeout = 60000)
    public void testRemainder_validParams_a17_b5() {
        given()
                .when()
                .get("/api/remainder/17/5")
                .then()
                .statusCode(200)
                .body("resultAsInt", notNullValue());
    }

    @Test(timeout = 60000)
    public void testRemainder_negativeA_aNeg9_b5() {
        given()
                .when()
                .get("/api/remainder/-9/5")
                .then()
                .statusCode(200)
                .body("resultAsInt", notNullValue());
    }

    @Test(timeout = 60000)
    public void testRemainder_boundary_a10000_bNeg1() {
        given()
                .when()
                .get("/api/remainder/10000/-1")
                .then()
                .statusCode(200)
                .body("resultAsInt", notNullValue());
    }
}