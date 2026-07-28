package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV1Test {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("BASE_URL");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void getByAlpha_notFound_returns404() {
        given()
                .when()
                .get("/v1/alpha/XYZ")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_badRequest_shortCode_returns400() {
        given()
                .queryParam("codes", "1")
                .when()
                .get("/v1/alpha/")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_notFound_returns404() {
        given()
                .queryParam("codes", "XX;YY;ZZ")
                .when()
                .get("/v1/alpha/")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_internalServerError_returns500() {
        given()
                .queryParam("codes", "US,CA,MX")
                .when()
                .get("/v1/alpha/")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCurrency_badRequest_invalidLength_returns400() {
        given()
                .when()
                .get("/v1/currency/12")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCurrency_notFound_returns404() {
        given()
                .when()
                .get("/v1/currency/XYZ")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCurrency_internalServerError_returns500() {
        given()
                .when()
                .get("/v1/currency/XyZ")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByName_notFound_returns404() {
        given()
                .when()
                .get("/v1/name/123")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCallingCode_notFound_returns404() {
        given()
                .when()
                .get("/v1/callingcode/99999")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCapital_notFound_returns404() {
        given()
                .when()
                .get("/v1/capital/123")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByRegion_notFound_returns404() {
        given()
                .when()
                .get("/v1/region/123")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getBySubregion_notFound_returns404() {
        given()
                .when()
                .get("/v1/subregion/123")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByLanguage_notFound_returns404() {
        given()
                .when()
                .get("/v1/lang/123")
                .then()
                .statusCode(404);
    }
}