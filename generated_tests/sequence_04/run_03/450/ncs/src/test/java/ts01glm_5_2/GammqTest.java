package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;

public class GammqTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testGserNormalPath() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/gammq/5.5/2.3")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGserXZeroPath() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/gammq/5.5/0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGcfNormalPath() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/gammq/0.001/1000.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGcfWithLargeX() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/gammq/1.0/100.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testInvalidAZero() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/gammq/0/2.0")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testInvalidXNegative() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/gammq/5.5/-1.0")
        .then()
            .statusCode(400);
    }
}