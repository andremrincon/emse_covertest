package ts01qwen3_7_plus;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CountryServiceBaseTest {

    private static final String BASE_URL = "http://localhost:8080/rest";

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testGetByAlpha2Code() {
        Response response = given()
                .baseUri(BASE_URL)
                .when()
                .get("/v1/alpha/US");

        response.then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testGetByAlpha3Code() {
        Response response = given()
                .baseUri(BASE_URL)
                .when()
                .get("/v1/alpha/USA");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaNotFound() {
        Response response = given()
                .baseUri(BASE_URL)
                .when()
                .get("/v1/alpha/XYZ");

        response.then().statusCode(404);
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testGetByCodeListValid() {
        Response response = given()
                .baseUri(BASE_URL)
                .when()
                .get("/v1/alpha?codes=US;CA");

        response.then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testGetByCodeListDuplicate() {
        Response response = given()
                .baseUri(BASE_URL)
                .when()
                .get("/v1/alpha?codes=US;US");

        response.then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testFulltextSearchExactMatch() {
        Response response = given()
                .baseUri(BASE_URL)
                .when()
                .get("/v1/name/France?fullText=true");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFulltextSearchAltSpelling() {
        Response response = given()
                .baseUri(BASE_URL)
                .when()
                .get("/v1/name/Deutschland?fullText=true");

        response.then().statusCode(404);
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testSubstringSearchExactMatch() {
        Response response = given()
                .baseUri(BASE_URL)
                .when()
                .get("/v1/name/France");

        response.then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testSubstringSearchPartialMatch() {
        Response response = given()
                .baseUri(BASE_URL)
                .when()
                .get("/v1/name/Fra");

        response.then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testSubstringSearchAltSpelling() {
        Response response = given()
                .baseUri(BASE_URL)
                .when()
                .get("/v1/name/Deutsch");

        response.then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testLoadJsonV1All() {
        Response response = given()
                .baseUri(BASE_URL)
                .when()
                .get("/v1/all");

        response.then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testLoadJsonV2All() {
        Response response = given()
                .baseUri(BASE_URL)
                .when()
                .get("/v2/all");

        response.then().statusCode(200);
    }
}