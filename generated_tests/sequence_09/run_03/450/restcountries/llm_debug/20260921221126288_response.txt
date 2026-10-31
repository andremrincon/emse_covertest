package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

public class CountryServiceBaseTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080/rest";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGetByAlphaTwoLetterSuccess_v1() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/{alphacode}", "US");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaThreeLetterSuccess_v2() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/alpha/{alphacode}", "USA");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaNotFound_v1() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/{alphacode}", "XYZ");
        act.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCodeList_semicolonDuplicates_v2() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response act = given().queryParam("codes", "US;US").when().get("/v2/alpha");
        act.then().body("size()", equalTo(1));
    }

    @Test(timeout = 60000)
    public void testGetByCodeList_comma_v1() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().queryParam("codes", "US,CA").when().get("/v1/alpha");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFullTextSearch_nameMatches_v1() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().queryParam("fullText", true).when().get("/v1/name/{name}", "France");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFullTextSearch_altSpelling_v1() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().queryParam("fullText", true).when().get("/v1/name/{name}", "United States of America");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubstringSearch_partialMatch_v1() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().queryParam("fullText", false).when().get("/v1/name/{name}", "Fran");
        act.then().body("name", hasItem("France"));
    }

    @Test(timeout = 60000)
    public void testCallingCode_v1_returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/callingcode/{callingcode}", "1");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testLoadJson_v1_all_endpoint() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/all");
        act.then().statusCode(200);
    }
}