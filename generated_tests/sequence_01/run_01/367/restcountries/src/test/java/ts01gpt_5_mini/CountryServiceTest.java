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
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testRegionalBlocAcronymUpperCaseReturns200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/regionalbloc/{regionalbloc}", "EU");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRegionalBlocAcronymCaseInsensitiveReturns200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/regionalbloc/{regionalbloc}", "nafta");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRegionalBlocInvalidNumericReturns404() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/regionalbloc/{regionalbloc}", "123");
        act.then().statusCode(404);
    }
}