package ts01qwen3_7_plus;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CountryServiceTest {

    private static final String BASE_URL = "http://localhost:8080/rest";

    @Test(timeout = 60000)
    public void testGetByLanguageIso639_1() {
        Response response = given()
                .baseUri(BASE_URL)
                .when()
                .get("/v2/lang/es");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByLanguageIso639_2() {
        Response response = given()
                .baseUri(BASE_URL)
                .when()
                .get("/v2/lang/spa");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBloc() {
        Response response = given()
                .baseUri(BASE_URL)
                .when()
                .get("/v2/regionalbloc/EU");

        response.then().statusCode(200);
    }
}