package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GammqTest {

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
    public void testGammqGserPathValidParams() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/gammq/5.5/2.3")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqGcfPathValidParams() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/gammq/0.001/1000.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqGserPathXZero() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/gammq/5.5/0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqGcfPathSmallAModerateX() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/gammq/0.001/2.3")
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
    public void testGammqInvalidXNegative() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/gammq/5.5/-1.0")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqInvalidAZero() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/gammq/0/2.3")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqNonNumericA() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/gammq/abc/2.3")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqNonNumericX() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/gammq/5.5/abc")
        .then()
            .statusCode(400);
    }
}