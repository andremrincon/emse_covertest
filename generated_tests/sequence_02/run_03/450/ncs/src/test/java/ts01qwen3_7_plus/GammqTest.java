package ts01qwen3_7_plus;

import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GammqTest {

    @Before
    public void setUp() {
        baseURI = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testGammqValidGserPath() {
        given()
        .when()
            .get("/api/gammq/5.5/2.3")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqValidGcfPath() {
        given()
        .when()
            .get("/api/gammq/0.001/1000.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqXZeroGserReturn() {
        given()
        .when()
            .get("/api/gammq/5.5/0.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqInvalidXNegative() {
        given()
        .when()
            .get("/api/gammq/5.5/-1.0")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqInvalidANonPositive() {
        given()
        .when()
            .get("/api/gammq/0.0/2.3")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqGcfExceedITMAX() {
        given()
        .when()
            .get("/api/gammq/100000.0/100001.0")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqGserExceedITMAX() {
        given()
        .when()
            .get("/api/gammq/10000000000.0/10000000000.9999")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqGcfFPMIN_d() {
        given()
        .when()
            .get("/api/gammq/1e-31/1e31")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqGcfFPMIN_c() {
        given()
        .when()
            .get("/api/gammq/1e-30/1e30")
        .then()
            .statusCode(200);
    }
}