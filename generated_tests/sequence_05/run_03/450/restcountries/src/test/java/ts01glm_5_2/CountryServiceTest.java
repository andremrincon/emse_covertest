package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
        RestAssured.basePath = "/rest";
    }

    @Test(timeout = 60000)
    public void testGetByLanguageWithTwoCharCode() {
        given()
            .when()
                .get("/v2/all")
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .get("/v2/lang/es")
            .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test(timeout = 60000)
    public void testGetByLanguageWithThreeCharCode() {
        given()
            .when()
                .get("/v2/all")
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .get("/v2/lang/spa")
            .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test(timeout = 60000)
    public void testGetByLanguageNotFound() {
        given()
            .when()
                .get("/v2/all")
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .get("/v2/lang/123")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBlocWithValidAcronym() {
        given()
            .when()
                .get("/v2/all")
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .get("/v2/regionalbloc/EU")
            .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBlocNotFound() {
        given()
            .when()
                .get("/v2/all")
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .get("/v2/regionalbloc/123")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBlocWithNafta() {
        given()
            .when()
                .get("/v2/all")
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .get("/v2/regionalbloc/NAFTA")
            .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }
}