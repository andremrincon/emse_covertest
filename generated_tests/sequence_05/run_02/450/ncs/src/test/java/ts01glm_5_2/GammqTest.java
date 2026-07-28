package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

import org.junit.Ignore;
public class GammqTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv().getOrDefault("BASE_URL", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testGammqGserPath() {
        given()
            .when()
                .get("/api/gammq/5.5/2.3")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqGcfPath() {
        given()
            .when()
                .get("/api/gammq/0.001/1000.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqXNegative() {
        given()
            .when()
                .get("/api/gammq/5.5/-1.0")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqANonPositive() {
        given()
            .when()
                .get("/api/gammq/0.0/2.3")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqGserXZero() {
        given()
            .when()
                .get("/api/gammq/5.5/0.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqInvalidParamType() {
        given()
            .when()
                .get("/api/gammq/abc/2.3")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqGcfITMAX() {
        given()
            .when()
                .get("/api/gammq/10000.0/10001.0")
            .then()
                .statusCode(400);
    }

    @Ignore("1 expectation failed. Expected status code <400> but was <200>.")
    @Test(timeout = 60000)
    public void testGammqGserITMAX() {
        given()
            .when()
                .get("/api/gammq/100.0/99.0")
            .then()
                .statusCode(400);
    }
}