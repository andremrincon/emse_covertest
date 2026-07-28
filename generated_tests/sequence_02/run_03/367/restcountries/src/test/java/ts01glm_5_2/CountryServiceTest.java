package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceTest {

    private static String baseUrl;

    @BeforeClass
    public static void setUp() {
        baseUrl = System.getProperty("baseUrl", System.getenv().getOrDefault("BASE_URL", "http://localhost:8080/rest"));
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void getByRegionalBloc_validAcronymEU_returnsCountries() {
        given()
                .when()
                .get("/v2/regionalbloc/EU")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByRegionalBloc_validAcronymNAFTA_returnsCountries() {
        given()
                .when()
                .get("/v2/regionalbloc/NAFTA")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByRegionalBloc_unknownAcronym_returnsNotFound() {
        given()
                .when()
                .get("/v2/regionalbloc/123")
                .then()
                .statusCode(404);
    }
}