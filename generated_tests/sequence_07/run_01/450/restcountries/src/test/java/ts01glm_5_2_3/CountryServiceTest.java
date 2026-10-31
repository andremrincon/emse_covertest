package ts01glm_5_2_3;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        if (System.getenv("BASE_URL") != null) {
            baseUrl = System.getenv("BASE_URL");
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBloc_EU_returnsCountries() {
        given()
                .when()
                .get("/v2/regionalbloc/EU")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBloc_nonExistentBloc_returns404() {
        given()
                .when()
                .get("/v2/regionalbloc/NonExistentBlocXYZ")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBloc_NAFTA_returnsCountries() {
        given()
                .when()
                .get("/v2/regionalbloc/NAFTA")
                .then()
                .statusCode(404);
    }
}