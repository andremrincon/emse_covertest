package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GammqTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testGammqGserPath() {
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
    public void testGammqXZeroGserBranch() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/gammq/5.5/0.0")
        .then()
            .statusCode(200);
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
    public void testGammqNonPositiveA() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/gammq/-1.0/2.3")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqInvalidAType() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/gammq/abc/2.3")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqGcfLargeX() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/gammq/5.5/1000.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqGserSmallA() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/gammq/0.001/0.0005")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqZeroA() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/gammq/0.0/2.3")
        .then()
            .statusCode(400);
    }
}