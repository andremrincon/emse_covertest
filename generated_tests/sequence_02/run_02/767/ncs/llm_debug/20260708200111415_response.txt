package ts01qwen3_7_plus;

import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GammqTest {

    private static final String BASE_URL = "http://localhost:8080";

    @Test(timeout = 60000)
    public void testGammqInvalidA() {
        given()
            .when()
                .get(BASE_URL + "/api/gammq/-1.0/2.0")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqInvalidX() {
        given()
            .when()
                .get(BASE_URL + "/api/gammq/2.0/-1.0")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqZeroA() {
        given()
            .when()
                .get(BASE_URL + "/api/gammq/0.0/2.0")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqZeroX() {
        given()
            .when()
                .get(BASE_URL + "/api/gammq/2.0/0.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqGserBranch() {
        given()
            .when()
                .get(BASE_URL + "/api/gammq/5.5/2.3")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqGcfBranch() {
        given()
            .when()
                .get(BASE_URL + "/api/gammq/0.001/1000.0")
            .then()
                .statusCode(200);
    }
}