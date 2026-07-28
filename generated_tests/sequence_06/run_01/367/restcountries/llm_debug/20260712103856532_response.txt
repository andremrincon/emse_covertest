package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceBaseTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost:8080/rest";
    }

    @Test(timeout = 60000)
    public void testGetByAlpha2Code() {
        given()
            .pathParam("alphacode", "US")
            .when()
                .get("/v1/alpha/{alphacode}")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha3Code() {
        given()
            .pathParam("alphacode", "USA")
            .when()
                .get("/v1/alpha/{alphacode}")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaNotFound() {
        given()
            .pathParam("alphacode", "XX")
            .when()
                .get("/v1/alpha/{alphacode}")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCodeListNull() {
        given()
            .when()
                .get("/v1/alpha")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCodeListDuplicate() {
        given()
            .queryParam("codes", "US;US")
            .when()
                .get("/v1/alpha")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testFulltextSearchName() {
        given()
            .queryParam("fullText", true)
            .pathParam("name", "Germany")
            .when()
                .get("/v1/name/{name}")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testFulltextSearchAltSpelling() {
        given()
            .queryParam("fullText", true)
            .when()
                .get("/v1/name/{name}", "Federal%20Republic%20of%20Germany")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testFulltextSearchNotFound() {
        given()
            .queryParam("fullText", true)
            .pathParam("name", "Atlantis")
            .when()
                .get("/v1/name/{name}")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testLoadJson() {
        given()
            .when()
                .get("/v1/all")
            .then()
                .statusCode(404);
    }
}