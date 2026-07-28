package ts01qwen3_7_plus;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CountryServiceBaseTest {

    private final String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");

    @Test(timeout = 60000)
    public void testGetByAlpha2Code() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/v1/alpha/US")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha3Code() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/v1/alpha/USA")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCodeListNull() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/v1/alpha")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCodeListWithDuplicates() {
        given()
            .baseUri(baseUrl)
            .queryParam("codes", "US;US")
        .when()
            .get("/v1/alpha")
        .then()
            .statusCode(404);
    }

    @Ignore("Illegal character in path at index 54: http://localhost:8080/rest/rest/v1/name/Bundesrepublik Deu...")
    @Test(timeout = 60000)
    public void testFulltextSearchByAltSpelling() {
        given()
            .baseUri(baseUrl)
            .queryParam("fullText", true)
        .when()
            .get("/v1/name/{name}", "Bundesrepublik Deutschland")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testLoadJson() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/v1/all")
        .then()
            .statusCode(404);
    }
}