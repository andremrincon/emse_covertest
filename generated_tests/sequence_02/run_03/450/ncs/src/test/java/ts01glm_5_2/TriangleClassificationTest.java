package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class TriangleClassificationTest {

    @BeforeClass
    public static void setUp() {
        String host = System.getProperty("server.host", "localhost");
        String port = System.getProperty("server.port", "8080");
        RestAssured.baseURI = "http://" + host + ":" + port;
    }

    @Test(timeout = 60000)
    public void testClassifyWithZeroSide() {
        given()
            .when()
                .get("/api/triangle/0/4/5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testClassifyWithNegativeSide() {
        given()
            .when()
                .get("/api/triangle/3/-1/5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testClassifyEquilateral() {
        given()
            .when()
                .get("/api/triangle/3/3/3")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testClassifyIsosceles() {
        given()
            .when()
                .get("/api/triangle/3/3/4")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testClassifyScalene() {
        given()
            .when()
                .get("/api/triangle/3/4/5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testClassifyNotATriangle() {
        given()
            .when()
                .get("/api/triangle/10/1/1")
            .then()
                .statusCode(200);
    }
}