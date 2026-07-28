package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceBaseTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testGetByAlpha2Code() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha3Code() {
        given()
            .when()
                .get("/v1/alpha/USA")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaNotFound() {
        given()
            .when()
                .get("/v1/alpha/XYZ")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCodeListNull() {
        given()
            .when()
                .get("/v1/alpha")
            .then()
                .statusCode(lessThan(500));
    }

    @Test(timeout = 60000)
    public void testGetByCodeListWithDuplicates() {
        given()
            .queryParam("codes", "US;US")
            .when()
                .get("/v1/alpha")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFulltextSearchExactName() {
        given()
            .queryParam("fullText", "true")
            .when()
                .get("/v1/name/Germany")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubstringSearchNameSubstring() {
        given()
            .queryParam("fullText", "false")
            .when()
                .get("/v1/name/Germ")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testLoadJson() {
        given()
            .when()
                .get("/v1/all")
            .then()
                .statusCode(200);
    }
}