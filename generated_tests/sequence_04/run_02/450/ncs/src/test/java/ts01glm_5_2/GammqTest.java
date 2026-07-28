package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class GammqTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("base.url", "http://localhost");
        RestAssured.port = Integer.parseInt(System.getProperty("server.port", "8080"));
    }

    @Test(timeout = 60000)
    public void testGserNormalPath() {
        given()
            .accept("application/json")
        .when()
            .get("/api/gammq/5.5/2.3")
        .then()
            .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testGcfNormalPath() {
        given()
            .accept("application/json")
        .when()
            .get("/api/gammq/0.001/1000.0")
        .then()
            .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testGserXZeroPath() {
        given()
            .accept("application/json")
        .when()
            .get("/api/gammq/5.5/0")
        .then()
            .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testInvalidA() {
        given()
            .accept("application/json")
        .when()
            .get("/api/gammq/-1.0/3.0")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testInvalidX() {
        given()
            .accept("application/json")
        .when()
            .get("/api/gammq/5.5/-1.0")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGcfLargeAOverflow() {
        given()
            .accept("application/json")
        .when()
            .get("/api/gammq/1e15/2e15")
        .then()
            .statusCode(200);
    }
}