package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CountryServiceTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
        RestAssured.basePath = "/rest";
    }

    @Test(timeout = 60000)
    public void testGetByLanguageWithTwoCharCode() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v2/lang/es")
        .then()
            .statusCode(200)
            .body("size()", greaterThan(0));
    }

    @Test(timeout = 60000)
    public void testGetByLanguageWithThreeCharCode() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v2/lang/spa")
        .then()
            .statusCode(200)
            .body("size()", greaterThan(0));
    }

    @Test(timeout = 60000)
    public void testGetByLanguageWithInvalidCode() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v2/lang/123")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBlocWithValidAcronym() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v2/regionalbloc/EU")
        .then()
            .statusCode(200)
            .body("size()", greaterThan(0));
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBlocWithInvalidAcronym() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v2/regionalbloc/123")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBlocWithNafta() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v2/regionalbloc/NAFTA")
        .then()
            .statusCode(200)
            .body("size()", greaterThan(0));
    }
}