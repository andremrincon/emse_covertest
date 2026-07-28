package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV1Test {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void getByAlpha_NotFound() {
        given()
                .when()
                .get("/v1/alpha/XX")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlpha_BadRequest() {
        given()
                .when()
                .get("/v1/alpha/1234")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void getByAlpha_Success() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_BadRequest() {
        given()
                .when()
                .get("/v1/alpha/?codes=1")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_NotFound() {
        given()
                .when()
                .get("/v1/alpha/?codes=XX;YY;ZZ")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_Success() {
        given()
                .when()
                .get("/v1/alpha/?codes=US;CA")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCurrency_BadRequest() {
        given()
                .when()
                .get("/v1/currency/12")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void getByCurrency_NotFound() {
        given()
                .when()
                .get("/v1/currency/XYZ")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByName_NotFound() {
        given()
                .when()
                .get("/v1/name/123")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCallingCode_NotFound() {
        given()
                .when()
                .get("/v1/callingcode/99999")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCapital_NotFound() {
        given()
                .when()
                .get("/v1/capital/123")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByRegion_NotFound() {
        given()
                .when()
                .get("/v1/region/123")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getBySubregion_NotFound() {
        given()
                .when()
                .get("/v1/subregion/123")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByLanguage_NotFound() {
        given()
                .when()
                .get("/v1/lang/123")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCurrency_Success() {
        given()
                .when()
                .get("/v1/currency/USD")
                .then()
                .statusCode(200);
    }
}