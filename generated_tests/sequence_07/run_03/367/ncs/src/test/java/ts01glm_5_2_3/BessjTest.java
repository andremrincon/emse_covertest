package ts01glm_5_2_3;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class BessjTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl != null) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void bessj_zeroX_returnsZero() {
        given()
            .when()
                .get("/api/bessj/3/0.0")
            .then()
                .statusCode(200)
                .body("resultAsDouble", equalTo(0.0));
    }

    @Test(timeout = 60000)
    public void bessj_negativeLargeX_coversBessj1NegativeBranch() {
        given()
            .when()
                .get("/api/bessj/3/-10.0")
            .then()
                .statusCode(200)
                .body("resultAsDouble", notNullValue());
    }

    @Test(timeout = 60000)
    public void bessj_axGreaterThanN_coversForwardRecurrence() {
        given()
            .when()
                .get("/api/bessj/3/2.5")
            .then()
                .statusCode(200)
                .body("resultAsDouble", notNullValue());
    }

    @Test(timeout = 60000)
    public void bessj_axLessThanOrEqualN_coversBackwardRecurrence() {
        given()
            .when()
                .get("/api/bessj/5/3.0")
            .then()
                .statusCode(200)
                .body("resultAsDouble", notNullValue());
    }

    @Test(timeout = 60000)
    public void bessj_invalidNString_returns400() {
        given()
            .when()
                .get("/api/bessj/abc/2.5")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void bessj_nLessThan2_returns400() {
        given()
            .when()
                .get("/api/bessj/-5/2.5")
            .then()
                .statusCode(400);
    }
}