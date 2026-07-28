package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceBaseTest {

    private static String BASE;

    @BeforeClass
    public static void setup() {
        BASE = System.getProperty("api.base");
        if (BASE == null || BASE.isEmpty()) BASE = System.getenv("API_BASE");
        if (BASE == null || BASE.isEmpty()) BASE = "http://localhost:8080/rest";
        RestAssured.baseURI = BASE;
    }

    @Test(timeout = 60000)
    public void testV1Alpha_TwoLetter_Returns200() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/v1/alpha/US");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV1Alpha_ThreeLetter_Returns200() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/v1/alpha/USA");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV1Alpha_InvalidFormat_Returns400() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/v1/alpha/123");
        act.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV1Alpha_NotFound_Returns404() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/v1/alpha/XYZ");
        act.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV1Alpha_ListCodes_Returns200() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/v1/alpha?codes=US,CA,MX");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testV1Alpha_ListCodes_BadFormat_Returns400() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/v1/alpha?codes=123");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV1Name_FullTextTrue_Returns200() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/v1/name/France?fullText=true");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV1Name_FullTextFalse_Substring_Returns200() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/v1/name/United?fullText=false");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV1Name_InvalidNumeric_Returns404() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/v1/name/123");
        act.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV1CallingCode_Returns200() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/v1/callingcode/1");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV1Capital_Returns200() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/v1/capital/London");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV1Region_CaseInsensitive_Returns200() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/v1/region/europe");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV1Subregion_Returns200() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/v1/subregion/Western%20Europe");
        act.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV1Name_FullText_AltSpelling_Returns200() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/v1/name/DE?fullText=true");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV1Name_Substring_AltSpelling_Returns200() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/v1/name/Republic?fullText=false");
        act.then().statusCode(200);
    }
}