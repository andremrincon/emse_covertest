package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("BASE_URL");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080/rest";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testRegionalBlocAcronymMatch_EU_returns200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v2/regionalbloc/EU").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRegionalBlocCaseInsensitive_eu_returns200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v2/regionalbloc/eu").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRegionalBlocNotFound_ZZZ_returns404() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v2/regionalbloc/ZZZ").then().statusCode(404);
    }
}