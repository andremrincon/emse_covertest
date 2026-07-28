package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertTrue;

public class ExpintTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testExpintN0Branch() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/expint/0/2.5")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintXZeroBranch() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/expint/3/0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintContinuedFractionConvergence() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/expint/3/2.5")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintSeriesNm1Zero() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/expint/1/0.1")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintSeriesNm1NonZero() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/expint/3/0.1")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintInvalidParameters() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/expint/-1/1")
        .then()
            .statusCode(400);
    }
}