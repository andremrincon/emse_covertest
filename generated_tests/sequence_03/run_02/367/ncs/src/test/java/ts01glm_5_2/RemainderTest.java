package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class RemainderTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void remainderPositiveAPositiveB() {
        given()
            .when()
                .get("/api/remainder/17/5")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void remainderPositiveANegativeB() {
        given()
            .when()
                .get("/api/remainder/17/-5")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void remainderNegativeAPositiveB() {
        given()
            .when()
                .get("/api/remainder/-9/4")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void remainderNegativeANegativeB() {
        given()
            .when()
                .get("/api/remainder/-9/-4")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void remainderAZero() {
        given()
            .when()
                .get("/api/remainder/0/5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void remainderBZero() {
        given()
            .when()
                .get("/api/remainder/5/0")
            .then()
                .statusCode(200);
    }
}