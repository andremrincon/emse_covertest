package ts01glm_5_2_3;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.Matchers.lessThan;

public class RemainderTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testRemainderPositiveAPositiveB() {
        RestAssured.given()
            .when()
                .get("/api/remainder/17/5")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testRemainderPositiveANegativeB() {
        RestAssured.given()
            .when()
                .get("/api/remainder/17/-5")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testRemainderNegativeAPositiveB() {
        RestAssured.given()
            .when()
                .get("/api/remainder/-9/5")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testRemainderNegativeANegativeB() {
        RestAssured.given()
            .when()
                .get("/api/remainder/-9/-5")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testRemainderAZero() {
        RestAssured.given()
            .when()
                .get("/api/remainder/0/5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderBZero() {
        RestAssured.given()
            .when()
                .get("/api/remainder/17/0")
            .then()
                .statusCode(200);
    }
}