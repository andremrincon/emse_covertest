package ts01qwen3_7_plus;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceBaseTest {

    private final String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");

    @Test(timeout = 60000)
    public void testGetByAlpha() {
        Response response = given()
                .baseUri(baseUrl)
                .when()
                .get("/v1/alpha/US");

        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCodeList() {
        Response response = given()
                .baseUri(baseUrl)
                .queryParam("codes", "US;CA")
                .when()
                .get("/v1/alpha");

        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testFulltextSearch() {
        Response response = given()
                .baseUri(baseUrl)
                .queryParam("fullText", true)
                .when()
                .get("/v1/name/France");

        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testSubstringSearch() {
        Response response = given()
                .baseUri(baseUrl)
                .queryParam("fullText", false)
                .when()
                .get("/v1/name/Fra");

        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testLoadJson() {
        Response response = given()
                .baseUri(baseUrl)
                .when()
                .get("/v1/all");

        response.then().statusCode(404);
    }
}