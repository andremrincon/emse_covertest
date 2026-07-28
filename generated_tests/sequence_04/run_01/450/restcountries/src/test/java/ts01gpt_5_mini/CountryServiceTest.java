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
            base = System.getenv("API_BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_TwoLetter_returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/lang/es");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_ThreeLetter_returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/lang/eng");
        act.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_InvalidCode_returns404() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/lang/123");
        act.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBloc_Acronym_returns200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/regionalbloc/EU");
        act.then().statusCode(200);
    }
}