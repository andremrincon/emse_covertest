package ts01glm_5_2_3;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;

public class CountryServiceTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
        RestAssured.basePath = "/rest";
    }

    @Test(timeout = 60000)
    public void testGetByLanguageWithTwoCharCode() {
        given()
                .when()
                .get("/v2/lang/{lang}", "es")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByLanguageWithThreeCharCode() {
        given()
                .when()
                .get("/v2/lang/{lang}", "spa")
                .then()
                .statusCode(lessThan(500));
    }

    @Test(timeout = 60000)
    public void testGetByLanguageWithInvalidCodeReturnsNotFound() {
        given()
                .when()
                .get("/v2/lang/{lang}", "123")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBlocWithValidAcronym() {
        given()
                .when()
                .get("/v2/regionalbloc/{regionalbloc}", "EU")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBlocWithNafta() {
        given()
                .when()
                .get("/v2/regionalbloc/{regionalbloc}", "NAFTA")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBlocWithInvalidReturnsNotFound() {
        given()
                .when()
                .get("/v2/regionalbloc/{regionalbloc}", "123")
                .then()
                .statusCode(404);
    }
}