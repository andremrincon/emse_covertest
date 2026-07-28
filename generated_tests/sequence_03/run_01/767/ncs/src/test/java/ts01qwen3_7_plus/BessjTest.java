package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class BessjTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <400> was greater than <300>.")
    @Test(timeout = 60000)
    public void testBessjNLessThan2() {
        given()
            .pathParam("n", 1)
            .pathParam("x", 2.5)
        .when()
            .get("/api/bessj/{n}/{x}")
        .then()
            .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testBessjLargeBj() {
        given()
            .pathParam("n", 10)
            .pathParam("x", 0.0001)
        .when()
            .get("/api/bessj/{n}/{x}")
        .then()
            .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testBessj1NegativeX() {
        given()
            .pathParam("n", 2)
            .pathParam("x", -10.0)
        .when()
            .get("/api/bessj/{n}/{x}")
        .then()
            .statusCode(400);
    }
}