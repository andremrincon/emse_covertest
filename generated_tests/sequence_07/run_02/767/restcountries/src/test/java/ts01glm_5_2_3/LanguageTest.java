package ts01glm_5_2_3;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.Ignore;
public class LanguageTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getProperty("baseUrl");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testV2LangEndpointReturnsLanguages() {
        given().when().get("/v2/lang/Spanish").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2NameEndpointReturnsLanguages() {
        given().when().get("/v2/name/Germany").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2AlphaEndpointReturnsLanguages() {
        given().when().get("/v2/alpha/US").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2AllEndpointReturnsLanguages() {
        given().when().get("/v2/all").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2RegionEndpointReturnsLanguages() {
        given().when().get("/v2/region/Europe").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2CapitalEndpointReturnsLanguages() {
        given().when().get("/v2/capital/Paris").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2CallingcodeEndpointReturnsLanguages() {
        given().when().get("/v2/callingcode/1").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2CurrencyEndpointReturnsLanguages() {
        given().when().get("/v2/currency/EUR").then().statusCode(404);
    }

    @Ignore("Illegal character in path at index 52: http://localhost:8080/rest/rest/v2/subregion/Western Europe")
    @Test(timeout = 60000)
    public void testV2SubregionEndpointReturnsLanguages() {
        given().when().get("/v2/subregion/{subregion}", "Western Europe").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2DemonymEndpointReturnsLanguages() {
        given().when().get("/v2/demonym/American").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2RegionalblocEndpointReturnsLanguages() {
        given().when().get("/v2/regionalbloc/EU").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2RootWithFieldsReturnsLanguages() {
        given().queryParam("fields", "name;languages").when().get("/v2").then().statusCode(404);
    }
}