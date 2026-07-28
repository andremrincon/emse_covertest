package ts01qwen3_7_plus;

import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CountryServiceTest {

    private final String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");

    @Test(timeout = 60000)
    public void testGetByRegionalBloc_Valid() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/v2/regionalbloc/EU")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBloc_Invalid() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/v2/regionalbloc/INVALID")
        .then()
            .statusCode(404);
    }
}