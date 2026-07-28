package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CountryServiceTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.basePath = System.getProperty("basePath", "/rest");
    }

    @Test(timeout = 60000)
    public void testGetByLanguageWithTwoCharCode() {
        given()
            .when()
                .get("/v2/lang/es")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByLanguageWithThreeCharCode() {
        given()
            .when()
                .get("/v2/lang/spa")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByLanguageWithInvalidLengthCode() {
        given()
            .when()
                .get("/v2/lang/abcd")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBlocWithEU() {
        given()
            .when()
                .get("/v2/regionalbloc/EU")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBlocWithNAFTA() {
        given()
            .when()
                .get("/v2/regionalbloc/NAFTA")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBlocWithNonExistentBloc() {
        given()
            .when()
                .get("/v2/regionalbloc/XYZ")
            .then()
                .statusCode(404);
    }
}