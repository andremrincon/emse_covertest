package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GammqTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getenv().getOrDefault("BASE_URL", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testGserXEqualsZero() {
        given()
            .when()
                .get("/api/gammq/1.0/0.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGserXPositiveLessThanAPlusOne() {
        given()
            .when()
                .get("/api/gammq/5.5/2.3")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGserLoopNotConverge() {
        given()
            .when()
                .get("/api/gammq/10000.0/10000.9")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGcfXGreaterThanOrEqualAPlusOne() {
        given()
            .when()
                .get("/api/gammq/0.001/1000.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGcfSmallDOrC() {
        given()
            .when()
                .get("/api/gammq/100.0/101.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGcfLoopNotConverge() {
        given()
            .when()
                .get("/api/gammq/10000.0/10001.0")
            .then()
                .statusCode(400);
    }
}