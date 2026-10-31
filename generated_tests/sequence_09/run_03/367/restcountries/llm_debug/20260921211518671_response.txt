package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceBaseTest {

    @BeforeClass
    public static void setup() {
        String env = System.getProperty("API_BASE");
        if (env == null || env.isEmpty()) {
            env = System.getenv("API_BASE");
        }
        if (env == null || env.isEmpty()) {
            env = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = env;
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_TwoLetterFound_v1() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/US");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_ThreeLetterFound_v2() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/alpha/USA");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_NotFound_v1() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/XYZ");
        act.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCodeList_CommaSeparated_v1() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().queryParam("codes", "US,CA,MX").when().get("/v1/alpha");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCodeList_SemicolonSeparated_v2() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().queryParam("codes", "US;CA").when().get("/v2/alpha");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFullTextSearch_AltSpelling_v1() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        String name = "United States of America";
        Response act = given().queryParam("fullText", "true").when().get("/v1/name/" + name);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubstringSearch_NameContains_v1() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        String name = "Fran";
        Response act = given().queryParam("fullText", "false").when().get("/v1/name/" + name);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubstringSearch_AlternativeContains_v1() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        String name = "States";
        Response act = given().queryParam("fullText", "false").when().get("/v1/name/" + name);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCode_Found_v1() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/callingcode/1");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCapital_Found_v1() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/capital/London");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegion_Found_v1() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/region/Europe");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_BadRequest_v1() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/123");
        act.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_BadFormat_500_v1() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        String codes = "[\"US\",\"CA\"]";
        Response act = given().queryParam("codes", codes).when().get("/v1/alpha");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testV2AlphaFieldsFiltering_v2() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        String fields = "name;capital;population";
        Response act = given().queryParam("fields", fields).when().get("/v2/alpha/US");
        act.then().statusCode(200);
    }
}