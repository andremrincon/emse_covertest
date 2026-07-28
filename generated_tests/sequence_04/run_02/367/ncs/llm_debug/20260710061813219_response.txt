package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.hamcrest.Matchers.lessThan;

public class RemainderTest {

    private static String baseUrl;

    @BeforeClass
    public static void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testRemainderAZero() {
        RestAssured.given()
            .baseUri(baseUrl)
        .when()
            .get("/api/remainder/0/5")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderBZero() {
        RestAssured.given()
            .baseUri(baseUrl)
        .when()
            .get("/api/remainder/17/0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderBothPositive() {
        RestAssured.given()
            .baseUri(baseUrl)
        .when()
            .get("/api/remainder/17/5")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderPositiveANegativeB() {
        RestAssured.given()
            .baseUri(baseUrl)
        .when()
            .get("/api/remainder/17/-5")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderNegativeAPositiveB() {
        RestAssured.given()
            .baseUri(baseUrl)
        .when()
            .get("/api/remainder/-9/5")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderBothNegative() {
        RestAssured.given()
            .baseUri(baseUrl)
        .when()
            .get("/api/remainder/-9/-5")
        .then()
            .statusCode(200);
    }
}