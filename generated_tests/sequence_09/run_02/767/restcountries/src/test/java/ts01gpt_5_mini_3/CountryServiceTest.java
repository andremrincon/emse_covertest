package ts01gpt_5_mini_3;

import org.junit.Test;
import static org.hamcrest.Matchers.lessThan;
import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import org.junit.Assert;

public class CountryServiceTest {

    private static final String BASE = System.getProperty("API_BASE_URL",
            System.getenv().containsKey("API_BASE_URL") ? System.getenv("API_BASE_URL") : "http://localhost:8080/rest");

    @Test(timeout = 60000)
    public void testGetByRegionalBloc_EU() {
        given().when().get(BASE + "/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/v2/regionalbloc/EU");
        Assert.assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBloc_NAFTA() {
        given().when().get(BASE + "/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/v2/regionalbloc/NAFTA");
        Assert.assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBloc_InvalidReturns404() {
        given().when().get(BASE + "/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/v2/regionalbloc/123");
        Assert.assertEquals(404, act.getStatusCode());
    }
}