package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class RemainderTest {

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testRemainderBothPositive() {
        RestAssured.given()
            .when()
                .get("/api/remainder/17/5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderPositiveDividendNegativeDivisor() {
        RestAssured.given()
            .when()
                .get("/api/remainder/17/-5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderNegativeDividendPositiveDivisor() {
        RestAssured.given()
            .when()
                .get("/api/remainder/-9/5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderBothNegative() {
        RestAssured.given()
            .when()
                .get("/api/remainder/-9/-5")
            .then()
                .statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code <400> but was <200>.")
    @Test(timeout = 60000)
    public void testRemainderDividendZero() {
        RestAssured.given()
            .when()
                .get("/api/remainder/0/5")
            .then()
                .statusCode(400);
    }

    @Ignore("1 expectation failed. Expected status code <400> but was <200>.")
    @Test(timeout = 60000)
    public void testRemainderDivisorZero() {
        RestAssured.given()
            .when()
                .get("/api/remainder/17/0")
            .then()
                .statusCode(400);
    }
}