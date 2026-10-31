package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.net.URLEncoder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceBaseTest {

    private static String baseUrl;

    @BeforeClass
    public static void setup() {
        baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080/rest";
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testGetByAlphaLengthNot2Or3() {
        given()
            .pathParam("alphacode", "A")
        .when()
            .get("/v1/alpha/{alphacode}")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCodeListNull() {
        given()
        .when()
            .get("/v1/alpha")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCodeListValid() {
        given()
            .queryParam("codes", "US;CA")
        .when()
            .get("/v1/alpha")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFulltextSearchNameMatch() {
        given()
            .pathParam("name", "Germany")
            .queryParam("fullText", "True")
        .when()
            .get("/v1/name/{name}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFulltextSearchAltSpellingMatch() throws Exception {
        String encoded = URLEncoder.encode("Federal Republic of Germany", "UTF-8").replace("+", "%20");
        given()
            .pathParam("name", encoded)
            .queryParam("fullText", "True")
        .when()
            .get("/v1/name/{name}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubstringSearchNameMatch() {
        given()
            .pathParam("name", "Germ")
            .queryParam("fullText", "False")
        .when()
            .get("/v1/name/{name}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testLoadJsonExecution() {
        given()
        .when()
            .get("/v1/all")
        .then()
            .statusCode(200);
    }
}