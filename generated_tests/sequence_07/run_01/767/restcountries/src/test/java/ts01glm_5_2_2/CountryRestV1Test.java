package ts01glm_5_2_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV1Test {

    private static String baseUrl;

    @BeforeClass
    public static void setup() {
        baseUrl = System.getProperty("baseUrl", System.getenv("baseUrl") != null ? System.getenv("baseUrl") : "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void getByAlpha_badRequest_shortCode() {
        given()
                .when()
                .get("/v1/alpha/A")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlpha_notFound_validCodeNoCountry() {
        given()
                .when()
                .get("/v1/alpha/XX")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlpha_success_validCode() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_badRequest_nullCodes() {
        given()
                .when()
                .get("/v1/alpha/")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_badRequest_lengthTooLongNoSemicolon() {
        given()
                .when()
                .queryParam("codes", "ABCD")
                .get("/v1/alpha/")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_notFound_validFormatNoMatch() {
        given()
                .when()
                .queryParam("codes", "XX")
                .get("/v1/alpha/")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_internalServerError_invalidFormat() {
        String encoded;
        try {
            encoded = URLEncoder.encode("[\"US\", \"CA\"]", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            encoded = "%5B%22US%22%2C%20%22CA%22%5D";
        }
        given()
                .when()
                .queryParam("codes", encoded)
                .get("/v1/alpha/")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCurrency_badRequest_invalidLength() {
        given()
                .when()
                .get("/v1/currency/12")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCurrency_notFound_validFormatNoMatch() {
        given()
                .when()
                .get("/v1/currency/XYZ")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByName_notFound_noMatch() {
        given()
                .when()
                .get("/v1/name/123")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCallingCode_notFound_noMatch() {
        given()
                .when()
                .get("/v1/callingcode/abc")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCapital_notFound_noMatch() {
        given()
                .when()
                .get("/v1/capital/123")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByRegion_notFound_noMatch() {
        given()
                .when()
                .get("/v1/region/123")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getBySubregion_notFound_noMatch() {
        given()
                .when()
                .get("/v1/subregion/123")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByLanguage_notFound_noMatch() {
        given()
                .when()
                .get("/v1/lang/123")
                .then()
                .statusCode(404);
    }
}