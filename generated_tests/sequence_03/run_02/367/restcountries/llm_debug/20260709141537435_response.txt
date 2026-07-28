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
        String baseUrl = System.getProperty("baseUrl", System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_TwoLetterCode() {
        Response response = given()
                .when()
                .get("/v2/lang/es");

        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_ThreeLetterCode() {
        Response response = given()
                .when()
                .get("/v2/lang/eng");

        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBloc_CoverLine121() {
        Response response = given()
                .when()
                .get("/v2/regionalbloc/EU");

        response.then().statusCode(404);
    }
}