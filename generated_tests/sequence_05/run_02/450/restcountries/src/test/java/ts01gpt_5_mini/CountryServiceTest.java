package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base", System.getenv("API_BASE_URL"));
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testRegionalBlocByAcronymReturns200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/regionalbloc/EU");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRegionalBlocByAcronymNaftaReturns200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/regionalbloc/NAFTA");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRegionalBlocNumericReturns404() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/regionalbloc/123");
        act.then().statusCode(404);
    }
}