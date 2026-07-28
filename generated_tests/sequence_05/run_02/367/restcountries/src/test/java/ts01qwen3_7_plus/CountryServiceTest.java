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
            baseUrl = System.getProperty("base.url", "http://localhost:8080/rest");
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBloc_EU() {
        given()
            .when()
            .get("/v2/all")
            .then()
            .statusCode(lessThan(300));

        Response response = given()
            .when()
            .get("/v2/regionalbloc/EU");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBloc_Invalid() {
        given()
            .when()
            .get("/v2/all")
            .then()
            .statusCode(lessThan(300));

        Response response = given()
            .when()
            .get("/v2/regionalbloc/123");

        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBloc_NAFTA() {
        given()
            .when()
            .get("/v2/all")
            .then()
            .statusCode(lessThan(300));

        Response response = given()
            .when()
            .get("/v2/regionalbloc/NAFTA");

        response.then().statusCode(200);
    }
}