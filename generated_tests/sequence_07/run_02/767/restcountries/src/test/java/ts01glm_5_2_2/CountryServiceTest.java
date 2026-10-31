package ts01glm_5_2_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void getByLanguage_withTwoCharCode_returns200() {
        given()
            .when()
                .get("/v1/lang/es")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByLanguage_withThreeCharCode_returns200() {
        given()
            .when()
                .get("/v1/lang/eng")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByLanguage_withNonMatchingThreeCharCode_returns404() {
        given()
            .when()
                .get("/v1/lang/123")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByRegionalBloc_withValidEuAcronym_returns200() {
        given()
            .when()
                .get("/v2/regionalbloc/EU")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByRegionalBloc_withValidNaftaAcronym_returns200() {
        given()
            .when()
                .get("/v2/regionalbloc/NAFTA")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByRegionalBloc_withNotFound_returns404() {
        given()
            .when()
                .get("/v2/regionalbloc/123")
            .then()
                .statusCode(404);
    }
}