package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Test;

public class GammqTest {

    static {
        RestAssured.baseURI = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testGcfNormalPath() {
        RestAssured.given()
                .when()
                .get("/api/gammq/1.0/5.0")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGcfExtremePath() {
        RestAssured.given()
                .when()
                .get("/api/gammq/1.0/1.0E40")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGserNormalPath() {
        RestAssured.given()
                .when()
                .get("/api/gammq/5.5/2.3")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGserExceptionPath() {
        RestAssured.given()
                .when()
                .get("/api/gammq/10000000000.0/10000000000.9")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGserZeroX() {
        RestAssured.given()
                .when()
                .get("/api/gammq/1.0/0.0")
                .then()
                .statusCode(200);
    }
}