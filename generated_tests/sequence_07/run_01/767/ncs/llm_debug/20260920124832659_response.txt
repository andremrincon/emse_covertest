package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class GammqTest {

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
    public void gammq_gserNormalPath_xLessThanAPlusOne() {
        given()
            .when()
            .get("/api/gammq/5.5/2.3")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void gammq_gcfPath_xGreaterThanOrEqualAPlusOne() {
        given()
            .when()
            .get("/api/gammq/0.001/1000.0")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void gammq_negativeX_throwsException() {
        given()
            .when()
            .get("/api/gammq/5.5/-1.0")
            .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void gammq_negativeA_throwsException() {
        given()
            .when()
            .get("/api/gammq/-1.0/2.3")
            .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void gammq_xZero_gserReturnsZero() {
        given()
            .when()
            .get("/api/gammq/5.5/0.0")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void gammq_invalidAType_returns400() {
        given()
            .when()
            .get("/api/gammq/abc/2.3")
            .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void gammq_xEqualsAPlusOne_gcfPath() {
        given()
            .when()
            .get("/api/gammq/5.5/6.5")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void gammq_aZero_throwsException() {
        given()
            .when()
            .get("/api/gammq/0.0/2.3")
            .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void gammq_largeA_gserITMAXExceeded() {
        given()
            .when()
            .get("/api/gammq/200.0/100.0")
            .then()
            .statusCode(200);
    }
}