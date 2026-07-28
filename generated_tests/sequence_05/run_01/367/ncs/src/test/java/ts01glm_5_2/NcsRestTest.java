package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class NcsRestTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testBessjNormalCase() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get("/api/bessj/3/2.5")
                .then()
                .statusCode(200)
                .body("resultAsDouble", notNullValue());
    }

    @Test(timeout = 60000)
    public void testBessjSmallX() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get("/api/bessj/3/1e-10")
                .then()
                .statusCode(200)
                .body("resultAsDouble", notNullValue());
    }

    @Test(timeout = 60000)
    public void testBessjLargeN() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get("/api/bessj/1000/2.5")
                .then()
                .statusCode(200)
                .body("resultAsDouble", notNullValue());
    }

    @Test(timeout = 60000)
    public void testBessjInvalidN() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get("/api/bessj/1/2.5")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFisherNormalCase() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get("/api/fisher/10/5/0.75")
                .then()
                .statusCode(200)
                .body("resultAsDouble", notNullValue());
    }

    @Test(timeout = 60000)
    public void testFisherZeroX() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get("/api/fisher/1/1/0.0")
                .then()
                .statusCode(200)
                .body("resultAsDouble", notNullValue());
    }

    @Test(timeout = 60000)
    public void testFisherLargeX() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get("/api/fisher/1/1/1.2")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqNormalCase() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get("/api/gammq/5.5/2.3")
                .then()
                .statusCode(200)
                .body("resultAsDouble", notNullValue());
    }

    @Test(timeout = 60000)
    public void testGammqLargeX() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get("/api/gammq/0.001/1000.0")
                .then()
                .statusCode(200)
                .body("resultAsDouble", notNullValue());
    }

    @Test(timeout = 60000)
    public void testGammqInvalidA() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get("/api/gammq/-1.0/2.0")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testRemainderNormalCase() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get("/api/remainder/17/5")
                .then()
                .statusCode(200)
                .body("resultAsInt", notNullValue());
    }

    @Test(timeout = 60000)
    public void testRemainderNegativeA() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get("/api/remainder/-9/4")
                .then()
                .statusCode(200)
                .body("resultAsInt", notNullValue());
    }
}