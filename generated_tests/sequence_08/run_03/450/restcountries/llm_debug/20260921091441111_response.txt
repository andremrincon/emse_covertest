package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceBaseTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testGetByAlpha3Code() {
        Response response = given()
                .when()
                .get("/v1/alpha/USA");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCodeListNull() {
        Response response = given()
                .when()
                .get("/v1/alpha");

        response.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCodeListUniqueCodes() {
        Response response = given()
                .queryParam("codes", "US;CA")
                .when()
                .get("/v1/alpha");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCodeListDuplicateCodes() {
        Response response = given()
                .queryParam("codes", "US;US")
                .when()
                .get("/v1/alpha");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFulltextSearchAltSpelling() {
        Response response = given()
                .queryParam("fullText", true)
                .when()
                .get("/v2/name/Federal%20Republic%20of%20Germany");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubstringSearchAltSpelling() {
        Response response = given()
                .queryParam("fullText", false)
                .when()
                .get("/v2/name/Bundesrepublik");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testLoadJsonInitialization() {
        Response response = given()
                .when()
                .get("/v2/all");

        response.then().statusCode(200);
    }
}