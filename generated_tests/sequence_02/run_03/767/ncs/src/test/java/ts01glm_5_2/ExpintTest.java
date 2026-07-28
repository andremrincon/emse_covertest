package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ExpintTest {

    @Before
    public void setUp() {
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
    public void testExpintNZeroBranch() {
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
    public void testExpintContinuedFractionConverges() {
        given()
            .accept(ContentType.JSON)
            .when()
                .get("/api/expint/3/2.5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintSeriesNm1ZeroPsiLoop() {
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
                .get("/api/expint/2/0.5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintErrorInvalidN() {
        given()
            .accept(ContentType.JSON)
            .when()
                .get("/api/expint/-1/2.5")
            .then()
                .statusCode(400);
    }
}