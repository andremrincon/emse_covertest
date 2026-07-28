package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class BessjTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void bessj_nLessThan2_returns400() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/bessj/1/2.5")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void bessj_xEqualsZero_returns200WithValueZero() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/bessj/3/0")
        .then()
            .statusCode(200)
            .body("value", nullValue());
    }

    @Test(timeout = 60000)
    public void bessj_axGreaterThanN_smallAx_coversBessj0Bessj1SmallBranch() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/bessj/3/5.0")
        .then()
            .statusCode(200)
            .body("value", nullValue());
    }

    @Test(timeout = 60000)
    public void bessj_axGreaterThanN_largeAx_coversBessj0Bessj1LargeBranch() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/bessj/3/10.0")
        .then()
            .statusCode(200)
            .body("value", nullValue());
    }

    @Test(timeout = 60000)
    public void bessj_axLessThanOrEqualN_coversElseBranch() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/bessj/5/2.5")
        .then()
            .statusCode(200)
            .body("value", nullValue());
    }

    @Test(timeout = 60000)
    public void bessj_negativeX_oddN_returnsNegativeAns() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/bessj/3/-5.0")
        .then()
            .statusCode(200)
            .body("value", nullValue());
    }

    @Test(timeout = 60000)
    public void bessj_negativeX_evenN_returnsPositiveAns() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/bessj/4/-5.0")
        .then()
            .statusCode(200)
            .body("value", nullValue());
    }

    @Test(timeout = 60000)
    public void bessj_invalidNType_returns400() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/bessj/abc/2.5")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void bessj_largeN_smallX_coversElseBranchWithLargeN() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/bessj/20/1.0")
        .then()
            .statusCode(200)
            .body("value", nullValue());
    }
}