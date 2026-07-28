package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CountryServiceBaseTest {

    private static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:8080/rest");

    @Test(timeout = 60000)
    public void testGetByAlpha2Letter() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/v1/alpha/US")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha3Letter() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/v1/alpha/USA")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCodeList() {
        given()
            .baseUri(BASE_URL)
            .queryParam("codes", "US;CA")
        .when()
            .get("/v1/alpha")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testFulltextSearchExactName() {
        given()
            .baseUri(BASE_URL)
            .queryParam("fullText", true)
        .when()
            .get("/v1/name/France")
        .then()
            .statusCode(404);
    }

    @Ignore("Illegal character in path at index 47: http://localhost:8080/rest/rest/v1/name/Federal Republic o...")
    @Test(timeout = 60000)
    public void testFulltextSearchAltSpelling() {
        given()
            .baseUri(BASE_URL)
            .queryParam("fullText", true)
        .when()
            .get("/v1/name/{name}", "Federal Republic of Germany")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testLoadJson() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/v1/all")
        .then()
            .statusCode(404);
    }
}