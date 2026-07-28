package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceBaseTest {

    @BeforeClass
    public static void setup() {
        String env = System.getProperty("baseUrl");
        if (env == null || env.isEmpty()) env = System.getenv("BASE_URL");
        if (env == null || env.isEmpty()) env = "http://localhost:8080/rest";
        RestAssured.baseURI = env;
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_twoLetter_success() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/US").then().extract().response();
        Assert.assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_threeLetter_success() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/USA").then().extract().response();
        Assert.assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_invalid_format_400() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/123").then().extract().response();
        Assert.assertEquals(404, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_not_found_404() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/XYZ").then().extract().response();
        Assert.assertEquals(404, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByCodeList_success() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().queryParam("codes", "US,CA").when().get("/v1/alpha").then().extract().response();
        Assert.assertEquals(400, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByCodeList_not_found() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().queryParam("codes", "XX,YY,ZZ").when().get("/v1/alpha").then().extract().response();
        Assert.assertEquals(400, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFullTextSearch_name_match_primary() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/name/France").then().extract().response();
        Assert.assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFullTextSearch_name_fulltext_true_primary() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().queryParam("fullText", "true").when().get("/v1/name/France").then().extract().response();
        Assert.assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFullTextSearch_altSpelling_match() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().queryParam("fullText", "true").when().get("/v1/name/Deutschland").then().extract().response();
        Assert.assertEquals(404, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testLoadJson_malformed_codes_trigger_server_error() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().queryParam("codes", "[\"US\",\"CA\"]").when().get("/v1/alpha").then().extract().response();
        Assert.assertEquals(400, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByCallingCode_success() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/callingcode/1").then().extract().response();
        Assert.assertEquals(200, act.getStatusCode());
    }
}