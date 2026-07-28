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
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testBessj_validN3X2_5() {
        given()
            .when()
                .get("/api/bessj/3/2.5")
            .then()
                .statusCode(200)
                .body("resultAsDouble", notNullValue());
    }

    @Test(timeout = 60000)
    public void testBessj_validN5XVerySmall() {
        given()
            .when()
                .get("/api/bessj/5/1e-10")
            .then()
                .statusCode(200)
                .body("resultAsDouble", notNullValue());
    }

    @Test(timeout = 60000)
    public void testBessj_validN10X0() {
        given()
            .when()
                .get("/api/bessj/10/0.0")
            .then()
                .statusCode(200)
                .body("resultAsDouble", notNullValue());
    }

    @Test(timeout = 60000)
    public void testFisher_validM10N5X0_75() {
        given()
            .when()
                .get("/api/fisher/10/5/0.75")
            .then()
                .statusCode(200)
                .body("resultAsDouble", notNullValue());
    }

    @Test(timeout = 60000)
    public void testFisher_validM1N1X0_0() {
        given()
            .when()
                .get("/api/fisher/1/1/0.0")
            .then()
                .statusCode(200)
                .body("resultAsDouble", notNullValue());
    }

    @Test(timeout = 60000)
    public void testFisher_validM2N3X0_5() {
        given()
            .when()
                .get("/api/fisher/2/3/0.5")
            .then()
                .statusCode(200)
                .body("resultAsDouble", notNullValue());
    }

    @Test(timeout = 60000)
    public void testGammq_validA5_5X2_3() {
        given()
            .when()
                .get("/api/gammq/5.5/2.3")
            .then()
                .statusCode(200)
                .body("resultAsDouble", notNullValue());
    }

    @Test(timeout = 60000)
    public void testGammq_validA0_001X1000() {
        given()
            .when()
                .get("/api/gammq/0.001/1000.0")
            .then()
                .statusCode(200)
                .body("resultAsDouble", notNullValue());
    }

    @Test(timeout = 60000)
    public void testGammq_validA1_0X0_5() {
        given()
            .when()
                .get("/api/gammq/1.0/0.5")
            .then()
                .statusCode(200)
                .body("resultAsDouble", notNullValue());
    }

    @Test(timeout = 60000)
    public void testRemainder_validA17B5() {
        given()
            .when()
                .get("/api/remainder/17/5")
            .then()
                .statusCode(200)
                .body("resultAsInt", notNullValue());
    }

    @Test(timeout = 60000)
    public void testRemainder_validANegative9B4() {
        given()
            .when()
                .get("/api/remainder/-9/4")
            .then()
                .statusCode(200)
                .body("resultAsInt", notNullValue());
    }

    @Test(timeout = 60000)
    public void testRemainder_validA0B7() {
        given()
            .when()
                .get("/api/remainder/0/7")
            .then()
                .statusCode(200)
                .body("resultAsInt", notNullValue());
    }
}