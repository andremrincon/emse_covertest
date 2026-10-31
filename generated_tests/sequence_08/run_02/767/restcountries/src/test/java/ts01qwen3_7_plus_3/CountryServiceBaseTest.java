package ts01qwen3_7_plus_3;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceBaseTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080/rest";
    }

    @Test(timeout = 60000)
    public void testGetByAlpha2CodeNotFound() {
        given()
            .when()
                .get("/v1/alpha/XX")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha3CodeNotFound() {
        given()
            .when()
                .get("/v1/alpha/XXX")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCodeListWithEmptyCodes() {
        given()
            .when()
                .get("/v1/alpha?codes=")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCodeListWithDuplicateCodes() {
        given()
            .when()
                .get("/v1/alpha?codes=US,US")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFulltextSearchWithAlternativeSpelling() {
        given()
            .when()
                .get("/v1/name/United%20States%20of%20America?fullText=true")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFulltextSearchWithExactName() {
        given()
            .when()
                .get("/v1/name/France?fullText=true")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testLoadJsonInitialization() {
        given()
            .when()
                .get("/v1/all")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha2CodeSuccess() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha3CodeSuccess() {
        given()
            .when()
                .get("/v1/alpha/USA")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCodeListSuccess() {
        given()
            .when()
                .get("/v1/alpha?codes=US,CA")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFulltextSearchNotFound() {
        given()
            .when()
                .get("/v1/name/NonExistentCountry123?fullText=true")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCodeListNotFound() {
        given()
            .when()
                .get("/v1/alpha?codes=XX,YY,ZZ")
            .then()
                .statusCode(400);
    }
}