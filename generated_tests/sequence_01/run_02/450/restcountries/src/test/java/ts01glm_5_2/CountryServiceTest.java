package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        String basePath = System.getProperty("basePath", "/rest");
        RestAssured.baseURI = baseUrl;
        RestAssured.basePath = basePath;
    }

    @Test(timeout = 60000)
    public void getByRegionalBloc_returnsCountriesForValidBlocEU() {
        given()
                .when()
                .get("/v2/regionalbloc/EU")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test(timeout = 60000)
    public void getByRegionalBloc_returnsCountriesForValidBlocNAFTA() {
        given()
                .when()
                .get("/v2/regionalbloc/NAFTA")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test(timeout = 60000)
    public void getByRegionalBloc_returns404ForNonExistentBloc() {
        given()
                .when()
                .get("/v2/regionalbloc/123")
                .then()
                .statusCode(404);
    }
}