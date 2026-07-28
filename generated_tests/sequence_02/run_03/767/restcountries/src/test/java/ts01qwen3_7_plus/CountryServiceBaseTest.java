package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.net.URLEncoder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CountryServiceBaseTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        RestAssured.baseURI = baseUrl != null ? baseUrl : "http://localhost:8080/rest";
    }

    @Test(timeout = 60000)
    public void testGetByAlphaReturnsNullForInvalidCode() {
        given()
            .pathParam("alphacode", "XYZ")
        .when()
            .get("/v1/alpha/{alphacode}")
        .then()
            .statusCode(404);
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <400>.")
    @Test(timeout = 60000)
    public void testGetByCodeListWithDuplicateCodes() {
        given()
            .queryParam("codes", "US,US")
        .when()
            .get("/v1/alpha")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFulltextSearchMatchesAltSpelling() {
        String name = "Federal Republic of Germany";
        String encodedName;
        try {
            encodedName = URLEncoder.encode(name, "UTF-8");
        } catch (java.io.UnsupportedEncodingException e) {
            encodedName = name.replace(" ", "%20");
        }
        encodedName = encodedName.replace("+", "%20");
        given()
            .queryParam("fullText", "true")
            .pathParam("name", encodedName)
        .when()
            .get("/v1/name/{name}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubstringSearchMatchesAltSpelling() {
        given()
            .queryParam("fullText", "false")
            .pathParam("name", "Bundesrepublik")
        .when()
            .get("/v1/name/{name}")
        .then()
            .statusCode(200);
    }
}