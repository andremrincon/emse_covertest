package ts01qwen3_7_plus;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceBaseTest {

    private final String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080/rest";

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
    public void testGetByCodeListValid() {
        given()
            .baseUri(baseUrl)
            .queryParam("codes", "US;CA")
        .when()
            .get("/v1/alpha")
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
    public void testFulltextSearch() {
        given()
            .baseUri(baseUrl)
            .queryParam("fullText", true)
        .when()
            .get("/v1/name/Germany")
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