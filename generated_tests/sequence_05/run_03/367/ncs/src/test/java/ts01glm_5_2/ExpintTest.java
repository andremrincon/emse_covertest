package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ExpintTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("BASE_URL");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testExpint_n0_xPositive_nEqualsZeroBranch() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/expint/{n}/{x}", 0, 2.5)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_negativeN_throwsException() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/expint/{n}/{x}", -1, 2.5)
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpint_n2_xZero_xEqualsZeroBranch() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/expint/{n}/{x}", 2, 0)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_n3_xGreaterThanOne_continuedFractionConverges() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/expint/{n}/{x}", 3, 2.5)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_n1_xLessThanOne_seriesPathNm1EqualsZero() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/expint/{n}/{x}", 1, 0.1)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_n3_xLessThanOne_seriesPathNm1NotZero() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/expint/{n}/{x}", 3, 0.1)
        .then()
            .statusCode(200);
    }
}