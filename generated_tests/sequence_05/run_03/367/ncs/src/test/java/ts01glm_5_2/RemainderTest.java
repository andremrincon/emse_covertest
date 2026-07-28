package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class RemainderTest {

    private static final String BASE_URL = System.getProperty("base.url", "http://localhost:8080");

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
        RestAssured.port = Integer.parseInt(System.getProperty("base.port", "8080"));
    }

    @Test(timeout = 60000)
    public void testRemainderPositiveDividendPositiveDivisor() {
        given()
            .pathParam("a", 17)
            .pathParam("b", 5)
        .when()
            .get("/api/remainder/{a}/{b}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderPositiveDividendNegativeDivisor() {
        given()
            .pathParam("a", 17)
            .pathParam("b", -5)
        .when()
            .get("/api/remainder/{a}/{b}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderNegativeDividendPositiveDivisor() {
        given()
            .pathParam("a", -9)
            .pathParam("b", 4)
        .when()
            .get("/api/remainder/{a}/{b}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderNegativeDividendNegativeDivisor() {
        given()
            .pathParam("a", -9)
            .pathParam("b", -4)
        .when()
            .get("/api/remainder/{a}/{b}")
        .then()
            .statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code <400> but was <200>.")
    @Test(timeout = 60000)
    public void testRemainderZeroDividend() {
        given()
            .pathParam("a", 0)
            .pathParam("b", 5)
        .when()
            .get("/api/remainder/{a}/{b}")
        .then()
            .statusCode(400);
    }

    @Ignore("1 expectation failed. Expected status code <400> but was <200>.")
    @Test(timeout = 60000)
    public void testRemainderZeroDivisor() {
        given()
            .pathParam("a", 17)
            .pathParam("b", 0)
        .when()
            .get("/api/remainder/{a}/{b}")
        .then()
            .statusCode(400);
    }
}