package ts01qwen3_7_plus;

import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CountryServiceTest {

    private static final String BASE_URI = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080/rest";

    @Test(timeout = 60000)
    public void testGetByLanguageWithTwoCharLength() {
        given()
            .baseUri(BASE_URI)
        .when()
            .get("/v2/lang/es")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByLanguageWithThreeCharLength() {
        given()
            .baseUri(BASE_URI)
        .when()
            .get("/v2/lang/eng")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBlocWithValidAcronym() {
        given()
            .baseUri(BASE_URI)
        .when()
            .get("/v2/regionalbloc/EU")
        .then()
            .statusCode(404);
    }
}