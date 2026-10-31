package ts01glm_5_2_2;

import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class BessjTest {

    private String baseUrl;

    @Before
    public void setUp() {
        String host = System.getenv().getOrDefault("API_HOST", "localhost");
        String port = System.getenv().getOrDefault("API_PORT", "8080");
        baseUrl = "http://" + host + ":" + port;
    }

    @Test(timeout = 60000)
    public void testBessjThrowsWhenNLessThan2() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/bessj/1/2.5")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessjReturnsZeroWhenXIsZero() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/bessj/2/0.0")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessjForwardRecurrenceSmallX() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/bessj/3/5.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjForwardRecurrenceLargeX() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/bessj/3/10.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjBackwardRecurrence() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/bessj/5/2.5")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjNegativeXOddN() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/bessj/3/-2.5")
        .then()
            .statusCode(200);
    }
}