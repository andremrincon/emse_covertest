package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ExpintTest {

    @BeforeClass
    public static void setUp() {
        String host = System.getProperty("host", "localhost");
        String port = System.getProperty("port", "8080");
        RestAssured.baseURI = "http://" + host + ":" + port;
    }

    @Test(timeout = 60000)
    public void expint_negativeN_throwsError() {
        given()
            .when()
                .get("/api/expint/-1/1")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void expint_nZero_xPositive_returns200() {
        given()
            .when()
                .get("/api/expint/0/2.5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void expint_nGreaterThanOne_xZero_returns200() {
        given()
            .when()
                .get("/api/expint/2/0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void expint_nOne_xZero_throwsError() {
        given()
            .when()
                .get("/api/expint/1/0")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void expint_xGreaterThanOne_continuedFractionConverges() {
        given()
            .when()
                .get("/api/expint/3/2.5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void expint_xLessThanOne_seriesPathWithPsiCalculation() {
        given()
            .when()
                .get("/api/expint/3/0.1")
            .then()
                .statusCode(200);
    }
}