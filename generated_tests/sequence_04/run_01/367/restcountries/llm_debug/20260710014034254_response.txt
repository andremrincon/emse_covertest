package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryTranslationsTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testGetCountryByAlphaCode() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetCountryByName() {
        given()
            .when()
                .get("/v1/name/France")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetCountryByCapital() {
        given()
            .when()
                .get("/v1/capital/London")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetCountryByRegion() {
        given()
            .when()
                .get("/v1/region/Europe")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetCountryByCurrency() {
        given()
            .when()
                .get("/v1/currency/USD")
            .then()
                .statusCode(404);
    }
}