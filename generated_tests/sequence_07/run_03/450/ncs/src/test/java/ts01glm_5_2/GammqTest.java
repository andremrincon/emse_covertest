package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GammqTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testGammqGserNormalPath() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/gammq/5.5/2.3")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqGcfPath() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/gammq/0.001/1000.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqGserXZero() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/gammq/5.5/0.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqInvalidANegative() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/gammq/-1.0/2.3")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqNegativeX() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/gammq/5.5/-1.0")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqInvalidTypeA() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/gammq/abc/2.3")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqGserNonConvergence() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/gammq/1000.0/999.0")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqGcfPathDifferentValues() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/gammq/3.0/5.0")
        .then()
            .statusCode(200);
    }
}