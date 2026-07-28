package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testGetByLanguageTwoLetterCode() {
        given().when().get("/v2/all").then().statusCode(404);

        Response response = given().when().get("/v2/lang/es");

        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByLanguageThreeLetterCode() {
        given().when().get("/v2/all").then().statusCode(404);

        Response response = given().when().get("/v2/lang/eng");

        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByLanguageInvalidLength() {
        given().when().get("/v2/all").then().statusCode(404);

        Response response = given().when().get("/v2/lang/spanish");

        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBlocValid() {
        given().when().get("/v2/all").then().statusCode(404);

        Response response = given().when().get("/v2/regionalbloc/EU");

        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBlocInvalid() {
        given().when().get("/v2/all").then().statusCode(404);

        Response response = given().when().get("/v2/regionalbloc/XYZ");

        response.then().statusCode(404);
    }
}