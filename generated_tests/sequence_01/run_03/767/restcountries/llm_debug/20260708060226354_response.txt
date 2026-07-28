package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.notNullValue;

public class CountryServiceTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null) {
            baseUrl = System.getenv("BASE_URL");
        }
        if (baseUrl == null) {
            baseUrl = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBloc_EU_ReturnsCountries() {
        given()
            .when()
                .get("/v2/regionalbloc/EU")
            .then()
                .statusCode(200)
                .body("name", notNullValue());
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBloc_NAFTA_ReturnsCountries() {
        given()
            .when()
                .get("/v2/regionalbloc/NAFTA")
            .then()
                .statusCode(200)
                .body("name", hasItem("United States of America"));
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBloc_InvalidBloc_Returns404() {
        given()
            .when()
                .get("/v2/regionalbloc/123")
            .then()
                .statusCode(404);
    }
}