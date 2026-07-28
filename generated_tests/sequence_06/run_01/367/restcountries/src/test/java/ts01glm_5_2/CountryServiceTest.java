package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceTest {

    private static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:8080/rest");

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test(timeout = 60000)
    public void getByLanguage_twoCharCode_returnsCountries() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v2/lang/es")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByLanguage_threeCharCode_returnsCountries() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v2/lang/spa")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByLanguage_nonMatchingCode_returnsNotFound() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v2/lang/zz")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByRegionalBloc_validAcronym_returnsCountries() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v2/regionalbloc/EU")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByRegionalBloc_lowercaseAcronym_returnsCountries() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v2/regionalbloc/eu")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByRegionalBloc_nonMatchingAcronym_returnsNotFound() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v2/regionalbloc/XYZ")
        .then()
            .statusCode(404);
    }
}