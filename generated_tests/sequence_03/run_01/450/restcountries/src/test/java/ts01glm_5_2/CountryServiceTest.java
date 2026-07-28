package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test(timeout = 60000)
    public void testGetByLanguageWithTwoCharCode() {
        given()
            .when()
                .get("/v2/lang/{lang}", "es")
            .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test(timeout = 60000)
    public void testGetByLanguageWithThreeCharCode() {
        given()
            .when()
                .get("/v2/lang/{lang}", "spa")
            .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test(timeout = 60000)
    public void testGetByLanguageWithInvalidLengthCode() {
        given()
            .when()
                .get("/v2/lang/{lang}", "123")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBlocWithAcronymMatch() {
        given()
            .when()
                .get("/v2/regionalbloc/{regionalbloc}", "EU")
            .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBlocWithNotFound() {
        given()
            .when()
                .get("/v2/regionalbloc/{regionalbloc}", "123")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBlocWithNaftaAcronym() {
        given()
            .when()
                .get("/v2/regionalbloc/{regionalbloc}", "NAFTA")
            .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }
}