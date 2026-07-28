package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.Matchers.lessThan;

public class RemainderTest {
    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testRemainderPositiveDividendPositiveDivisor() {
        RestAssured.given()
            .baseUri(baseUrl)
            .when()
            .get("/api/remainder/17/5")
            .then()
            .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testRemainderPositiveDividendNegativeDivisor() {
        RestAssured.given()
            .baseUri(baseUrl)
            .when()
            .get("/api/remainder/17/-5")
            .then()
            .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testRemainderNegativeDividendPositiveDivisor() {
        RestAssured.given()
            .baseUri(baseUrl)
            .when()
            .get("/api/remainder/-9/5")
            .then()
            .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testRemainderNegativeDividendNegativeDivisor() {
        RestAssured.given()
            .baseUri(baseUrl)
            .when()
            .get("/api/remainder/-9/-5")
            .then()
            .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testRemainderZeroDividend() {
        RestAssured.given()
            .baseUri(baseUrl)
            .when()
            .get("/api/remainder/0/5")
            .then()
            .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testRemainderZeroDivisor() {
        RestAssured.given()
            .baseUri(baseUrl)
            .when()
            .get("/api/remainder/5/0")
            .then()
            .statusCode(200);
    }
}