package ts01qwen3_7_plus;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class GammqTest {

    @Test(timeout = 60000)
    public void testGammqValidGser() {
        given()
                .when()
                .get("/api/gammq/5.5/2.3")
                .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testGammqValidGcf() {
        given()
                .when()
                .get("/api/gammq/1.0/1000.0")
                .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testGammqInvalidA() {
        given()
                .when()
                .get("/api/gammq/-1.0/2.0")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqInvalidX() {
        given()
                .when()
                .get("/api/gammq/5.5/-1.0")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqXZero() {
        given()
                .when()
                .get("/api/gammq/5.5/0.0")
                .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testGammqGserITMAX() {
        given()
                .when()
                .get("/api/gammq/1000.0/999.0")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqGcfITMAX() {
        given()
                .when()
                .get("/api/gammq/1000.0/2000.0")
                .then()
                .statusCode(lessThan(300));
    }
}