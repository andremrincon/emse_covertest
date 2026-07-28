package ts01qwen3_7_plus;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceBaseTest {

    private final String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080/rest";

    @Test(timeout = 60000)
    public void testGetByAlpha2() {
        given().when().get(baseUrl + "/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get(baseUrl + "/v1/alpha/{alphacode}", "US");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha3() {
        given().when().get(baseUrl + "/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get(baseUrl + "/v1/alpha/{alphacode}", "USA");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaNotFound() {
        given().when().get(baseUrl + "/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get(baseUrl + "/v1/alpha/{alphacode}", "XYZ");
        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCodeList() {
        given().when().get(baseUrl + "/v1/all").then().statusCode(lessThan(300));
        Response response = given().queryParam("codes", "US;CA").when().get(baseUrl + "/v1/alpha");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFulltextSearchNameMatch() {
        given().when().get(baseUrl + "/v1/all").then().statusCode(lessThan(300));
        Response response = given().queryParam("fullText", "true").when().get(baseUrl + "/v1/name/{name}", "France");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFulltextSearchAltSpellingMatch() {
        given().when().get(baseUrl + "/v1/all").then().statusCode(lessThan(300));
        Response response = given().queryParam("fullText", "true").when().get(baseUrl + "/v1/name/{name}", "Federal%20Republic%20of%20Germany");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFulltextSearchNoMatch() {
        given().when().get(baseUrl + "/v1/all").then().statusCode(lessThan(300));
        Response response = given().queryParam("fullText", "true").when().get(baseUrl + "/v1/name/{name}", "Atlantis");
        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testLoadJson() {
        given().when().get(baseUrl + "/v1/region/Europe").then().statusCode(lessThan(300));
        Response response = given().when().get(baseUrl + "/v1/all");
        response.then().statusCode(200);
    }
}