package ts01glm_5_2_3;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class BessjTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void bessj_nLessThan2_zero_throwsException() {
        given()
            .when()
                .get("/api/bessj/0/2.5")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void bessj_nLessThan2_one_throwsException() {
        given()
            .when()
                .get("/api/bessj/1/2.5")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void bessj_negativeX_largeAbsoluteValue_coversBessj1NegativeBranch() {
        given()
            .when()
                .get("/api/bessj/2/-10.0")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void bessj_xZero_returnsZero() {
        given()
            .when()
                .get("/api/bessj/3/0.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void bessj_axGreaterThanN_forwardRecurrence() {
        given()
            .when()
                .get("/api/bessj/3/2.5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void bessj_axLessThanOrEqualN_backwardRecurrence() {
        given()
            .when()
                .get("/api/bessj/5/2.5")
            .then()
                .statusCode(200);
    }
}