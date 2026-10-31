package ts01glm_5_2_3;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class GammqTest {

    private static String baseUrl;

    @BeforeClass
    public static void setUp() {
        baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("baseUrl");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testGammqGserNormalPath() {
        given()
                .pathParam("a", 5.5)
                .pathParam("x", 2.3)
                .when()
                .get("/api/gammq/{a}/{x}")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqGcfPath() {
        given()
                .pathParam("a", 1.0)
                .pathParam("x", 1000.0)
                .when()
                .get("/api/gammq/{a}/{x}")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqGserXZeroPath() {
        given()
                .pathParam("a", 5.5)
                .pathParam("x", 0.0)
                .when()
                .get("/api/gammq/{a}/{x}")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqInvalidA() {
        given()
                .pathParam("a", -1.0)
                .pathParam("x", 2.3)
                .when()
                .get("/api/gammq/{a}/{x}")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqInvalidType() {
        given()
                .pathParam("a", "abc")
                .pathParam("x", 2.3)
                .when()
                .get("/api/gammq/{a}/{x}")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqGcfLargeA() {
        given()
                .pathParam("a", 100.0)
                .pathParam("x", 200.0)
                .when()
                .get("/api/gammq/{a}/{x}")
                .then()
                .statusCode(200);
    }
}