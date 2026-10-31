package ts01qwen3_7_plus_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceBaseTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        RestAssured.baseURI = baseUrl != null ? baseUrl : "http://localhost:8080/rest";
    }

    @Test(timeout = 60000)
    public void testLoadJson() {
        given()
        .when()
            .get("/v1/all")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha2() {
        given()
        .when()
            .get("/v1/alpha/US")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha3() {
        given()
        .when()
            .get("/v1/alpha/USA")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaInvalidLength() {
        given()
        .when()
            .get("/v1/alpha/1234")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaNotFound() {
        given()
        .when()
            .get("/v1/alpha/XX")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCodeList() {
        given()
        .when()
            .get("/v1/alpha?codes=US,CA")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCodeListDuplicates() {
        given()
        .when()
            .get("/v1/alpha?codes=US,US")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFulltextSearch() {
        given()
        .when()
            .get("/v1/name/Germany?fullText=true")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFulltextSearchAltSpelling() {
        given()
        .when()
            .get("/v1/name/Federal%20Republic%20of%20Germany?fullText=true")
        .then()
            .statusCode(404);
    }
}