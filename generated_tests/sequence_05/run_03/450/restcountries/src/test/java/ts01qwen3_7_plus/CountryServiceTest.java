package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CountryServiceTest {

    @Before
    public void setUp() {
        String baseUri = System.getenv("BASE_URI") != null ? System.getenv("BASE_URI") : "http://localhost:8080";
        String basePath = System.getenv("BASE_PATH") != null ? System.getenv("BASE_PATH") : "/rest";
        RestAssured.baseURI = baseUri;
        RestAssured.basePath = basePath;
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBloc_EU() {
        given()
            .when()
                .get("/v2/regionalbloc/EU")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBloc_NAFTA() {
        given()
            .when()
                .get("/v2/regionalbloc/NAFTA")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBloc_NotFound() {
        given()
            .when()
                .get("/v2/regionalbloc/123")
            .then()
                .statusCode(404);
    }
}