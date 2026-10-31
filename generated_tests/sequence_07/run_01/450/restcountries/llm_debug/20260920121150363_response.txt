package ts01glm_5_2_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ResponseEntityTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testAlphaCodeNotFoundReturnsResponseEntity() {
        given()
                .when()
                .get("/v1/alpha/XYZ")
                .then()
                .statusCode(404)
                .body(equalTo(""));
    }

    @Test(timeout = 60000)
    public void testNameNotFoundReturnsResponseEntityMessage() {
        given()
                .when()
                .get("/v1/name/123")
                .then()
                .statusCode(404)
                .body(equalTo(""));
    }

    @Test(timeout = 60000)
    public void testCallingCodeNotFoundReturnsResponseEntityStatus() {
        given()
                .when()
                .get("/v1/callingcode/abc")
                .then()
                .statusCode(404)
                .body(equalTo(""));
    }

    @Test(timeout = 60000)
    public void testCapitalNotFoundReturnsResponseEntityMessage() {
        given()
                .when()
                .get("/v1/capital/123")
                .then()
                .statusCode(404)
                .body(equalTo(""));
    }

    @Test(timeout = 60000)
    public void testRegionNotFoundReturnsResponseEntityStatus() {
        given()
                .when()
                .get("/v1/region/123")
                .then()
                .statusCode(404)
                .body(equalTo(""));
    }

    @Test(timeout = 60000)
    public void testCurrencyNotFoundReturnsResponseEntityMessage() {
        given()
                .when()
                .get("/v1/currency/XYZ")
                .then()
                .statusCode(404)
                .body(equalTo(""));
    }
}