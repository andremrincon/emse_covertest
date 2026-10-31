package ts01qwen3_7_plus_3;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GammqTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("test.base.uri", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testGammqGserNormal() {
        String a = "5.5";
        String x = "2.3";

        given()
            .basePath("/api/gammq")
        .when()
            .get("/{a}/{x}", a, x)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqGcfNormal() {
        String a = "5.5";
        String x = "1000.0";

        given()
            .basePath("/api/gammq")
        .when()
            .get("/{a}/{x}", a, x)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqGcfSmallD() {
        String a = "5.5";
        String x = "1e40";

        given()
            .basePath("/api/gammq")
        .when()
            .get("/{a}/{x}", a, x)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqInvalidA() {
        String a = "-1.0";
        String x = "2.3";

        given()
            .basePath("/api/gammq")
        .when()
            .get("/{a}/{x}", a, x)
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqInvalidX() {
        String a = "5.5";
        String x = "-1.0";

        given()
            .basePath("/api/gammq")
        .when()
            .get("/{a}/{x}", a, x)
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqGserSmallX() {
        String a = "0.001";
        String x = "0.0001";

        given()
            .basePath("/api/gammq")
        .when()
            .get("/{a}/{x}", a, x)
        .then()
            .statusCode(200);
    }
}