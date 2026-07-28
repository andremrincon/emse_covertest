package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class GammqTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            String envUrl = System.getenv("BASE_URL");
            if (envUrl != null && !envUrl.isEmpty()) {
                RestAssured.baseURI = envUrl;
            } else {
                RestAssured.baseURI = "http://localhost:8080";
            }
        }
    }

    @Test(timeout = 60000)
    public void testGserNormalPathXLessThanAPlusOne() {
        given()
            .when()
                .get("/api/gammq/5.5/2.3")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGcfNormalPathXGreaterOrEqualAPlusOne() {
        given()
            .when()
                .get("/api/gammq/0.001/1000.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGserXZeroPath() {
        given()
            .when()
                .get("/api/gammq/5.5/0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExeInvalidANegative() {
        given()
            .when()
                .get("/api/gammq/-1.0/3.0")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExeInvalidXNegative() {
        given()
            .when()
                .get("/api/gammq/5.5/-1.0")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExeNonNumericA() {
        given()
            .when()
                .get("/api/gammq/abc/2.3")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExeNonNumericX() {
        given()
            .when()
                .get("/api/gammq/5.5/abc")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGserSmallAAndXLessThanAPlusOne() {
        given()
            .when()
                .get("/api/gammq/0.001/0.0005")
            .then()
                .statusCode(200);
    }
}