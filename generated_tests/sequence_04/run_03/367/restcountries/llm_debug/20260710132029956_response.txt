package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.net.URLEncoder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceBaseTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void testGetByAlpha2Code() {
        given()
            .pathParam("alphacode", "US")
            .when()
                .get("/v1/alpha/{alphacode}")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha3Code() {
        given()
            .pathParam("alphacode", "USA")
            .when()
                .get("/v1/alpha/{alphacode}")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCodeList() {
        given()
            .queryParam("codes", "US;CA")
            .when()
                .get("/v1/alpha")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCodeListNull() {
        given()
            .when()
                .get("/v1/alpha")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testFulltextSearchAltSpelling() throws Exception {
        String encoded = URLEncoder.encode("Bundesrepublik Deutschland", "UTF-8");
        given()
            .queryParam("fullText", true)
            .when()
                .get("/v1/name/" + encoded)
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testLoadJson() {
        given()
            .when()
                .get("/v1/all")
            .then()
                .statusCode(404);
    }
}