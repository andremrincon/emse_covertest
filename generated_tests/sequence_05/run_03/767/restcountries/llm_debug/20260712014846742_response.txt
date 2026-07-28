package ts01qwen3_7_plus;

import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CountryServiceBaseTest {

    private static final String BASE_URI = "http://localhost:8080/rest";

    @Test(timeout = 60000)
    public void testGetByAlpha2Code() {
        given()
            .baseUri(BASE_URI)
        .when()
            .get("/v2/alpha/US")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha3Code() {
        given()
            .baseUri(BASE_URI)
        .when()
            .get("/v2/alpha/USA")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCodeList() {
        given()
            .baseUri(BASE_URI)
            .queryParam("codes", "US;CA")
        .when()
            .get("/v2/alpha")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFulltextSearch() {
        given()
            .baseUri(BASE_URI)
            .queryParam("fullText", "true")
        .when()
            .get("/v2/name/Germany")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testLoadJson() {
        given()
            .baseUri(BASE_URI)
        .when()
            .get("/v2/all")
        .then()
            .statusCode(200);
    }
}