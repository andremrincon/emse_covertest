package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class FisherTest {

    @Before
    public void setup() {
        String baseUrl = System.getenv("BASE_URL");
        RestAssured.baseURI = baseUrl != null ? baseUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testFisher_m1_n5_x075() {
        given()
            .when()
            .get("/api/fisher/1/5/0.75")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_m3_n5_x075() {
        given()
            .when()
            .get("/api/fisher/3/5/0.75")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_m10_n5_x075() {
        given()
            .when()
            .get("/api/fisher/10/5/0.75")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_m2_n1_x00() {
        given()
            .when()
            .get("/api/fisher/2/1/0.0")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_invalid_m() {
        given()
            .when()
            .get("/api/fisher/abc/5/0.75")
            .then()
            .statusCode(400);
    }
}