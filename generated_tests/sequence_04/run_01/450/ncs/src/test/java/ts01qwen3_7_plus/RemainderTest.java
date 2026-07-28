package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class RemainderTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost";
        }
        String basePort = System.getenv("BASE_PORT");
        if (basePort != null && !basePort.isEmpty()) {
            RestAssured.port = Integer.parseInt(basePort);
        } else {
            RestAssured.port = 8080;
        }
    }

    @Test(timeout = 60000)
    public void testRemainderPositiveANegativeB() {
        given()
            .when()
                .get("/api/remainder/17/-9")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderNegativeAPositiveB() {
        given()
            .when()
                .get("/api/remainder/-9/5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderNegativeANegativeB() {
        given()
            .when()
                .get("/api/remainder/-9/-5")
            .then()
                .statusCode(200);
    }
}