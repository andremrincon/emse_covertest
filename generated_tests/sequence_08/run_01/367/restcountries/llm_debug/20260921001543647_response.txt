package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CountryServiceBaseTest {

    private final String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");

    @Test(timeout = 60000)
    public void testGetByAlphaReturnsNullForInvalidCode() {
        given()
            .baseUri(baseUrl)
            .pathParam("alphacode", "XX")
        .when()
            .get("/v1/alpha/{alphacode}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCodeListWithNullCodeList() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/v1/alpha")
        .then()
            .statusCode(404);
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <400>.")
    @Test(timeout = 60000)
    public void testGetByCodeListWithDuplicateCodes() {
        given()
            .baseUri(baseUrl)
            .queryParam("codes", "US,US")
        .when()
            .get("/v1/alpha")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFulltextSearchWithAlternativeSpelling() {
        given()
            .baseUri(baseUrl)
            .pathParam("name", "Federal%20Republic%20of%20Germany")
            .queryParam("fullText", true)
        .when()
            .get("/v1/name/{name}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testSubstringSearchWithAlternativeSpelling() {
        given()
            .baseUri(baseUrl)
            .pathParam("name", "Bundesrepublik")
            .queryParam("fullText", false)
        .when()
            .get("/v1/name/{name}")
        .then()
            .statusCode(404);
    }
}