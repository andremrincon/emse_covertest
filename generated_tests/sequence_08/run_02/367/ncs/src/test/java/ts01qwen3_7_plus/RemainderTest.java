package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

public class RemainderTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = System.getenv().getOrDefault("BASE_URL", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testRemainderAEqualsZero() {
        RestAssured.given()
                .pathParam("a", 0)
                .pathParam("b", 5)
                .when()
                .get("/api/remainder/{a}/{b}")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderBEqualsZero() {
        RestAssured.given()
                .pathParam("a", 5)
                .pathParam("b", 0)
                .when()
                .get("/api/remainder/{a}/{b}")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderAPositiveBPositive() {
        RestAssured.given()
                .pathParam("a", 17)
                .pathParam("b", 5)
                .when()
                .get("/api/remainder/{a}/{b}")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderAPositiveBNegative() {
        RestAssured.given()
                .pathParam("a", 17)
                .pathParam("b", -5)
                .when()
                .get("/api/remainder/{a}/{b}")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderANegativeBPositive() {
        RestAssured.given()
                .pathParam("a", -17)
                .pathParam("b", 5)
                .when()
                .get("/api/remainder/{a}/{b}")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderANegativeBNegative() {
        RestAssured.given()
                .pathParam("a", -17)
                .pathParam("b", -5)
                .when()
                .get("/api/remainder/{a}/{b}")
                .then()
                .statusCode(200);
    }
}