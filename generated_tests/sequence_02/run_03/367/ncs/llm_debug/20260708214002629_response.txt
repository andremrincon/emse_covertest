package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class GammqTest {

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testGserNormalPath() {
        given()
            .when()
                .get("/api/gammq/5.5/2.3")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testGcfNormalPath() {
        given()
            .when()
                .get("/api/gammq/0.001/1000.0")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testInvalidAValue() {
        given()
            .when()
                .get("/api/gammq/-1.0/3.0")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testInvalidXValue() {
        given()
            .when()
                .get("/api/gammq/5.5/-1.0")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGserITMAXExceeded() {
        given()
            .when()
                .get("/api/gammq/1000/999")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGcfITMAXExceeded() {
        given()
            .when()
                .get("/api/gammq/1000/1001")
            .then()
                .statusCode(200);
    }
}