package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class RemainderTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        RestAssured.baseURI = (baseUrl != null && !baseUrl.isEmpty()) ? baseUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testRemainderPositiveAPositiveB() {
        given()
            .when()
                .get("/api/remainder/17/5")
            .then()
                .statusCode(200);
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
                .get("/api/remainder/-17/5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderNegativeANegativeB() {
        given()
            .when()
                .get("/api/remainder/-17/-5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderInvalidA() {
        given()
            .when()
                .get("/api/remainder/abc/5")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testRemainderInvalidB() {
        given()
            .when()
                .get("/api/remainder/17/abc")
            .then()
                .statusCode(400);
    }
}