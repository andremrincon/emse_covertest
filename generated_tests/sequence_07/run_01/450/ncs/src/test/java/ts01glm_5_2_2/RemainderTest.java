package ts01glm_5_2_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class RemainderTest {

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = System.getProperty("baseUri", "http://localhost");
        RestAssured.port = Integer.parseInt(System.getProperty("port", "8080"));
    }

    @Test(timeout = 60000)
    public void testRemainderPositiveDividendPositiveDivisor() {
        given()
            .when()
                .get("/api/remainder/17/5")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testRemainderPositiveDividendNegativeDivisor() {
        given()
            .when()
                .get("/api/remainder/17/-5")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testRemainderNegativeDividendPositiveDivisor() {
        given()
            .when()
                .get("/api/remainder/-9/5")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testRemainderNegativeDividendNegativeDivisor() {
        given()
            .when()
                .get("/api/remainder/-9/-5")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testRemainderZeroDividend() {
        given()
            .when()
                .get("/api/remainder/0/5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderZeroDivisor() {
        given()
            .when()
                .get("/api/remainder/17/0")
            .then()
                .statusCode(200);
    }
}