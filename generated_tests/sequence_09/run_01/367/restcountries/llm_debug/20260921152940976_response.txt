package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class CountryServiceTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            String env = System.getenv("API_BASE");
            base = (env == null || env.isEmpty()) ? "http://localhost:8080/rest" : env;
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testV1LangTwoLetter_es() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/lang/{lang}", "es");
        assertEquals(200, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testV2LangThreeLetter_eng() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response response = given().when().get("/v2/lang/{lang}", "eng");
        assertEquals(200, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testV1LangInvalidNumeric_123_returns404() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/lang/{lang}", "123");
        assertEquals(404, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testV2RegionalBlocAcronym_EU() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response response = given().when().get("/v2/regionalbloc/{regionalbloc}", "EU");
        assertEquals(200, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testV2RegionalBlocNotFound_XYZ_returns404() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response response = given().when().get("/v2/regionalbloc/{regionalbloc}", "XYZ");
        assertEquals(404, response.getStatusCode());
    }
}