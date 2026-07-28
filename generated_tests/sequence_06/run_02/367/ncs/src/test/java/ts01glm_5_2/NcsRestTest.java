package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.Matchers.*;

public class NcsRestTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testBessjValidN3X2_5() {
        RestAssured.given()
            .pathParam("n", 3)
            .pathParam("x", 2.5)
        .when()
            .get("/api/bessj/{n}/{x}")
        .then()
            .statusCode(200)
            .body("resultAsDouble", notNullValue());
    }

    @Test(timeout = 60000)
    public void testBessjValidN3X0() {
        RestAssured.given()
            .pathParam("n", 3)
            .pathParam("x", 0.0)
        .when()
            .get("/api/bessj/{n}/{x}")
        .then()
            .statusCode(200)
            .body("resultAsDouble", notNullValue());
    }

    @Test(timeout = 60000)
    public void testBessjValidN1000XSmall() {
        RestAssured.given()
            .pathParam("n", 1000)
            .pathParam("x", 1e-10)
        .when()
            .get("/api/bessj/{n}/{x}")
        .then()
            .statusCode(200)
            .body("resultAsDouble", notNullValue());
    }

    @Test(timeout = 60000)
    public void testFisherValidM10N5X0_75() {
        RestAssured.given()
            .pathParam("m", 10)
            .pathParam("n", 5)
            .pathParam("x", 0.75)
        .when()
            .get("/api/fisher/{m}/{n}/{x}")
        .then()
            .statusCode(200)
            .body("resultAsDouble", notNullValue());
    }

    @Test(timeout = 60000)
    public void testFisherValidM1N1X0() {
        RestAssured.given()
            .pathParam("m", 1)
            .pathParam("n", 1)
            .pathParam("x", 0.0)
        .when()
            .get("/api/fisher/{m}/{n}/{x}")
        .then()
            .statusCode(200)
            .body("resultAsDouble", notNullValue());
    }

    @Test(timeout = 60000)
    public void testFisherValidM1N1X0_5() {
        RestAssured.given()
            .pathParam("m", 1)
            .pathParam("n", 1)
            .pathParam("x", 0.5)
        .when()
            .get("/api/fisher/{m}/{n}/{x}")
        .then()
            .statusCode(200)
            .body("resultAsDouble", notNullValue());
    }

    @Test(timeout = 60000)
    public void testGammqValidA5_5X2_3() {
        RestAssured.given()
            .pathParam("a", 5.5)
            .pathParam("x", 2.3)
        .when()
            .get("/api/gammq/{a}/{x}")
        .then()
            .statusCode(200)
            .body("resultAsDouble", notNullValue());
    }

    @Test(timeout = 60000)
    public void testGammqValidASmallXLarge() {
        RestAssured.given()
            .pathParam("a", 0.001)
            .pathParam("x", 1000.0)
        .when()
            .get("/api/gammq/{a}/{x}")
        .then()
            .statusCode(200)
            .body("resultAsDouble", notNullValue());
    }

    @Test(timeout = 60000)
    public void testGammqValidASmallXSmall() {
        RestAssured.given()
            .pathParam("a", 0.001)
            .pathParam("x", 0.001)
        .when()
            .get("/api/gammq/{a}/{x}")
        .then()
            .statusCode(200)
            .body("resultAsDouble", notNullValue());
    }

    @Test(timeout = 60000)
    public void testRemainderValidA17B5() {
        RestAssured.given()
            .pathParam("a", 17)
            .pathParam("b", 5)
        .when()
            .get("/api/remainder/{a}/{b}")
        .then()
            .statusCode(200)
            .body("resultAsInt", equalTo(2));
    }

    @Test(timeout = 60000)
    public void testRemainderValidNegativeA() {
        RestAssured.given()
            .pathParam("a", -9)
            .pathParam("b", 5)
        .when()
            .get("/api/remainder/{a}/{b}")
        .then()
            .statusCode(200)
            .body("resultAsInt", notNullValue());
    }

    @Test(timeout = 60000)
    public void testRemainderValidA17B4() {
        RestAssured.given()
            .pathParam("a", 17)
            .pathParam("b", 4)
        .when()
            .get("/api/remainder/{a}/{b}")
        .then()
            .statusCode(200)
            .body("resultAsInt", equalTo(1));
    }
}