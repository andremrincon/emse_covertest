package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.lessThan;

public class RemainderTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getProperty("BASE_URL", "http://localhost:8080");
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testRemainderAZero() {
        when()
            .get("/api/remainder/0/5")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderBZero() {
        when()
            .get("/api/remainder/5/0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderPositiveANegativeB() {
        when()
            .get("/api/remainder/17/-5")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderNegativeAPositiveB() {
        when()
            .get("/api/remainder/-9/5")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderNegativeANegativeB() {
        when()
            .get("/api/remainder/-9/-5")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderNegativeASmallNegativeB() {
        when()
            .get("/api/remainder/-3/-1")
        .then()
            .statusCode(200);
    }
}