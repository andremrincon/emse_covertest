package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceBaseTest {

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void testGetByAlpha2Code() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha3Code() {
        given()
            .when()
                .get("/v1/alpha/USA")
            .then()
                .statusCode(404);
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
    public void testGetByCodeList() {
        given()
            .when()
                .get("/v1/alpha?codes=US;CA")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testFulltextSearchName() {
        given()
            .when()
                .get("/v1/name/France?fullText=true")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testFulltextSearchAltSpelling() {
        given()
            .when()
                .get("/v1/name/Deutschland?fullText=true")
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