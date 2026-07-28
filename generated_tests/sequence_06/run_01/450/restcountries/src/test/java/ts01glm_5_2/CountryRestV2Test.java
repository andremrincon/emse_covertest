package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV2Test {

    private static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:8080/rest");

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test(timeout = 60000)
    public void getByAlpha_404_notFound() {
        given()
        .when()
            .get("/v2/alpha/XYZ")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlpha_200_withFields() {
        given()
        .when()
            .get("/v2/alpha/US")
        .then()
            .statusCode(lessThan(300));

        given()
        .when()
            .get("/v2/alpha/US?fields=name;capital")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_200_withFields() {
        given()
        .when()
            .get("/v2/alpha?codes=US")
        .then()
            .statusCode(lessThan(300));

        given()
        .when()
            .get("/v2/alpha?codes=US;CA&fields=name;capital")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_400_badRequest() {
        given()
        .when()
            .get("/v2/alpha?codes=123")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_404_notFound() {
        given()
        .when()
            .get("/v2/alpha?codes=XX;YY;ZZ")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCurrency_400_badRequest() {
        given()
        .when()
            .get("/v2/currency/123")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCurrency_404_notFound() {
        given()
        .when()
            .get("/v2/currency/XYZ")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByName_404_notFound() {
        given()
        .when()
            .get("/v2/name/123")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCallingCode_404_notFound() {
        given()
        .when()
            .get("/v2/callingcode/99999")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCapital_404_notFound() {
        given()
        .when()
            .get("/v2/capital/12345")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByRegion_404_notFound() {
        given()
        .when()
            .get("/v2/region/123")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getBySubRegion_404_notFound() {
        given()
        .when()
            .get("/v2/subregion/123")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByLanguage_404_notFound() {
        given()
        .when()
            .get("/v2/lang/123")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByDemonym_404_notFound() {
        given()
        .when()
            .get("/v2/demonym/123")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByRegionalBloc_404_notFound() {
        given()
        .when()
            .get("/v2/regionalbloc/123")
        .then()
            .statusCode(404);
    }
}