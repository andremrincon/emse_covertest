package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class FisherTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testFisher_m1_n1_x0() {
        given()
            .when()
                .get("/api/fisher/1/1/0.0")
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
    public void testFisher_m5_n4_x05() {
        given()
            .when()
                .get("/api/fisher/5/4/0.5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_m4_n4_x05() {
        given()
            .when()
                .get("/api/fisher/4/4/0.5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_m4_n4_xLarge() {
        given()
            .when()
                .get("/api/fisher/4/4/100000.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_m4_n4_xSmall() {
        given()
            .when()
                .get("/api/fisher/4/4/0.00001")
            .then()
                .statusCode(200);
    }
}