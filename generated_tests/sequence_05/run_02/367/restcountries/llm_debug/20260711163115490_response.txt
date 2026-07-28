package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.greaterThan;

public class CountryServiceTest {

    @BeforeClass
    public static void setupBaseUri() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testRegionalBlocEU_returns200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/regionalbloc/{regionalbloc}", "EU");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRegionalBlocNAFTA_returnsNonEmptyBody() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/regionalbloc/{regionalbloc}", "NAFTA");
        resp.then().body("size()", greaterThan(0));
    }

    @Test(timeout = 60000)
    public void testRegionalBlocInvalid_returns404() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/regionalbloc/{regionalbloc}", "123");
        resp.then().statusCode(404);
    }
}