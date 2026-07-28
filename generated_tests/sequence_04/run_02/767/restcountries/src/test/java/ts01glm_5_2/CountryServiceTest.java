package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
        RestAssured.basePath = "/rest";
    }

    @Test(timeout = 60000)
    public void testGetByLanguageTwoCharV1() {
        given()
            .when()
                .get("/v1/lang/es")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByLanguageTwoCharV2() {
        given()
            .when()
                .get("/v2/lang/es")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByLanguageThreeCharV2() {
        given()
            .when()
                .get("/v2/lang/spa")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBlocEU() {
        given()
            .when()
                .get("/v2/regionalbloc/EU")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBlocNAFTA() {
        given()
            .when()
                .get("/v2/regionalbloc/NAFTA")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBlocNotFound() {
        given()
            .when()
                .get("/v2/regionalbloc/XYZ")
            .then()
                .statusCode(404);
    }
}