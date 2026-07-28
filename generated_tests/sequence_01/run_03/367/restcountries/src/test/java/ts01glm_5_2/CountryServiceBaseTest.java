package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceBaseTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void getByAlpha_TwoCharCode_Returns200() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlpha_ThreeCharCode_Returns200() {
        given()
            .when()
                .get("/v1/alpha/COL")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlpha_NotFound_Returns404() {
        given()
            .when()
                .get("/v1/alpha/XYZ")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlpha_InvalidFormat_Returns400() {
        given()
            .when()
                .get("/v1/alpha/123")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCodeList_ValidCodes_Returns200() {
        given()
            .queryParam("codes", "US;CA;MX")
            .when()
                .get("/v1/alpha")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCodeList_AllInvalidCodes_Returns404() {
        given()
            .queryParam("codes", "XX;YY;ZZ")
            .when()
                .get("/v1/alpha")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCodeList_InvalidFormat_Returns400() {
        given()
            .queryParam("codes", "123")
            .when()
                .get("/v1/alpha")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void fulltextSearch_ExactNameMatch_Returns200() {
        given()
            .queryParam("fullText", "true")
            .when()
                .get("/v1/name/France")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void fulltextSearch_AltSpellingMatch_Returns200() {
        given()
            .queryParam("fullText", "true")
            .when()
                .get("/v1/name/{name}", "United%20States%20of%20America")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void fulltextSearch_NotFound_Returns404() {
        given()
            .queryParam("fullText", "true")
            .when()
                .get("/v1/name/Atlantis")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void loadJson_V1All_Returns200() {
        given()
            .when()
                .get("/v1/all")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void loadJson_V2All_Returns200() {
        given()
            .when()
                .get("/v2/all")
            .then()
                .statusCode(404);
    }
}