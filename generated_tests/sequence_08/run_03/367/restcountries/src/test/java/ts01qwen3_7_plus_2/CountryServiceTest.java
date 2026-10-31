package ts01qwen3_7_plus_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("test.base.uri", System.getenv().getOrDefault("BASE_URI", "http://localhost:8080"));
        RestAssured.basePath = System.getProperty("test.base.path", System.getenv().getOrDefault("BASE_PATH", "/rest"));
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBloc_EU() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v2/regionalbloc/EU");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBloc_NAFTA() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v2/regionalbloc/NAFTA");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBloc_Invalid() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v2/regionalbloc/123");
        response.then().statusCode(404);
    }
}