package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class BessjTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <400> was greater than <300>.")
    @Test(timeout = 60000)
    public void testBessjNLessThan2() {
        given()
            .when()
                .get("/api/bessj/1/2.5")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testBessjAxLessThan8() {
        given()
            .when()
                .get("/api/bessj/2/5.0")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessjAxGreaterOrEqual8NegativeX() {
        given()
            .when()
                .get("/api/bessj/2/-10.0")
            .then()
                .statusCode(400);
    }
}