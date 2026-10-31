package ts01qwen3_7_plus;

import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GammqTest {

    private static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:8080");

    @Test(timeout = 60000)
    public void testExeXNegative() {
        given()
            .when()
            .get(BASE_URL + "/api/gammq/5.5/-1.0")
            .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExeANegative() {
        given()
            .when()
            .get(BASE_URL + "/api/gammq/-1.0/2.3")
            .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExeAZero() {
        given()
            .when()
            .get(BASE_URL + "/api/gammq/0.0/2.3")
            .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGserXZero() {
        given()
            .when()
            .get(BASE_URL + "/api/gammq/5.5/0.0")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGserNormal() {
        given()
            .when()
            .get(BASE_URL + "/api/gammq/5.5/2.3")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGcfNormal() {
        given()
            .when()
            .get(BASE_URL + "/api/gammq/0.001/1000.0")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGcfITMAX() {
        given()
            .when()
            .get(BASE_URL + "/api/gammq/10000.0/10001.0")
            .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGserITMAX() {
        given()
            .when()
            .get(BASE_URL + "/api/gammq/1000.0/1000.5")
            .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGcfExtreme() {
        given()
            .when()
            .get(BASE_URL + "/api/gammq/1.0E-10/1.0")
            .then()
            .statusCode(200);
    }
}