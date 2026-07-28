package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ExpintTest {

    private static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:8080");

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test(timeout = 60000)
    public void testExpintNegativeNReturns400() {
        given()
            .when()
                .get("/api/expint/-1/2.5")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpintNZeroValidXReturns200() {
        given()
            .when()
                .get("/api/expint/0/2.5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintXZeroValidNReturns200() {
        given()
            .when()
                .get("/api/expint/2/0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintXGreaterThanOneReturns200() {
        given()
            .when()
                .get("/api/expint/3/2.5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintXLessThanOneNm1ZeroReturns200() {
        given()
            .when()
                .get("/api/expint/1/0.1")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintXLessThanOneNm1NonZeroReturns200() {
        given()
            .when()
                .get("/api/expint/3/0.1")
            .then()
                .statusCode(200);
    }
}