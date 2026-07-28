package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class BessjTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testBessjNLessThan2Returns400() {
        given()
            .when()
                .get("/api/bessj/1/2.5")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessjNA3X2p5Returns200() {
        given()
            .when()
                .get("/api/bessj/3/2.5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjNA3XNegative2p5Returns200() {
        given()
            .when()
                .get("/api/bessj/3/-2.5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjNA3X0Returns200() {
        given()
            .when()
                .get("/api/bessj/3/0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjNA3XNegative10Returns200() {
        given()
            .when()
                .get("/api/bessj/3/-10")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjNA2XSmallReturns200() {
        given()
            .when()
                .get("/api/bessj/2/0.0000000001")
            .then()
                .statusCode(400);
    }
}