package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class NcsRestTest {

    @BeforeClass
    public static void setUp() {
        String host = System.getProperty("server.host", "localhost");
        String port = System.getProperty("server.port", "8080");
        RestAssured.baseURI = "http://" + host;
        RestAssured.port = Integer.parseInt(port);
        RestAssured.basePath = "/api";
    }

    @Test(timeout = 60000)
    public void testBessjWithXZero() {
        given()
            .when()
                .get("/bessj/3/0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjWithVerySmallX() {
        given()
            .when()
                .get("/bessj/3/0.0000000001")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjWithLargeN() {
        given()
            .when()
                .get("/bessj/100/2.5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherWithXZero() {
        given()
            .when()
                .get("/fisher/10/5/0.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherWithXOne() {
        given()
            .when()
                .get("/fisher/10/5/1.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherWithSmallMAndN() {
        given()
            .when()
                .get("/fisher/1/1/0.5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderWithNegativeA() {
        given()
            .when()
                .get("/remainder/-9/5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderWithNegativeB() {
        given()
            .when()
                .get("/remainder/17/-4")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderWithBothNegative() {
        given()
            .when()
                .get("/remainder/-17/-5")
            .then()
                .statusCode(200);
    }
}