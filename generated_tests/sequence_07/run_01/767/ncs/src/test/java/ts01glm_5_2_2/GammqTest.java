package ts01glm_5_2_2;

import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class GammqTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testGserNormalPath() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/gammq/5.5/2.3")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGserXZero() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/gammq/5.5/0.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGcfNormalPath() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/gammq/0.001/1000.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGcfFpminHit() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/gammq/1.0/1e31")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGserThrowNonConvergence() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/gammq/10000000000.0/10000000000.0")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGcfThrowNonConvergence() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/gammq/10000000000.0/10000000001.0")
        .then()
            .statusCode(400);
    }
}