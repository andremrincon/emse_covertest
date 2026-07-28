package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class GammqTest {

    static {
        String baseUrl = System.getProperty("base.url");
        if (baseUrl == null) {
            baseUrl = System.getenv("BASE_URL");
        }
        if (baseUrl == null) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testGammq_Gser_Normal() {
        given()
            .pathParam("a", 5.5)
            .pathParam("x", 2.3)
        .when()
            .get("/api/gammq/{a}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_Gcf_Normal() {
        given()
            .pathParam("a", 0.001)
            .pathParam("x", 1000.0)
        .when()
            .get("/api/gammq/{a}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_Gser_ZeroX() {
        given()
            .pathParam("a", 5.0)
            .pathParam("x", 0.0)
        .when()
            .get("/api/gammq/{a}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_Gser_NoConvergence() {
        given()
            .pathParam("a", 1000.0)
            .pathParam("x", 999.0)
        .when()
            .get("/api/gammq/{a}/{x}")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammq_Gcf_Extreme() {
        given()
            .pathParam("a", 1e-10)
            .pathParam("x", 1e10)
        .when()
            .get("/api/gammq/{a}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_InvalidArgs() {
        given()
            .pathParam("a", -1.0)
            .pathParam("x", 3.0)
        .when()
            .get("/api/gammq/{a}/{x}")
        .then()
            .statusCode(400);
    }
}