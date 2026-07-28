package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;

public class CountryServiceTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testGetByLanguageWith3CharCode() {
        given()
            .when()
                .get("/v2/lang/spa")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByLanguageWith2CharCode() {
        given()
            .when()
                .get("/v2/lang/es")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByLanguageNotFound() {
        given()
            .when()
                .get("/v2/lang/123")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBlocEU() {
        given()
            .when()
                .get("/v2/regionalbloc/EU")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBlocNAFTA() {
        given()
            .when()
                .get("/v2/regionalbloc/NAFTA")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBlocNotFound() {
        given()
            .when()
                .get("/v2/regionalbloc/123")
            .then()
                .statusCode(404);
    }
}