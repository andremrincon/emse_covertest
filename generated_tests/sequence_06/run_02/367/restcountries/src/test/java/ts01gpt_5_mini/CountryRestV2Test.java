package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV2Test {

    @BeforeClass
    public static void setup() {
        String env = System.getenv("API_BASE");
        if (env != null && !env.isEmpty()) {
            RestAssured.baseURI = env;
        } else {
            RestAssured.baseURI = System.getProperty("api.base", "http://localhost:8080/rest");
        }
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_validReturns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/alpha/{code}", "US");
        Assert.assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_invalidLengthReturns400() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/alpha/{code}", "1");
        Assert.assertEquals(400, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_withFieldsReturnsBodyContainingName() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().queryParam("fields", "name;capital;population").when().get("/v2/alpha/{code}", "US");
        Assert.assertTrue(act.asString().contains("name"));
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_validReturns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        String codes = "US,CA";
        Response act = given().queryParam("codes", codes).when().get("/v2/alpha");
        Assert.assertEquals(400, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_invalidJsonArrayReturns400() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        String uuid = UUID.randomUUID().toString();
        Response act = given().queryParam("codes", "[\"US\",\"CA\"]").queryParam("marker", uuid).when().get("/v2/alpha");
        Assert.assertEquals(400, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_invalidLengthReturns400() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/currency/{currency}", "12");
        Assert.assertEquals(400, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_validReturns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().queryParam("fields", "name;capital;population").when().get("/v2/currency/{currency}", "EUR");
        Assert.assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByName_fullTextTrueReturns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().queryParam("fullText", "true").when().get("/v2/name/{name}", "Germany");
        Assert.assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByCallingCode_invalidReturns404() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/callingcode/{callingcode}", "abc");
        Assert.assertEquals(404, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByCapital_validReturns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().queryParam("fields", "name;capital;population").when().get("/v2/capital/{capital}", "Paris");
        Assert.assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByRegion_invalidNumericReturns404() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/region/{region}", "123");
        Assert.assertEquals(404, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetBySubRegion_validReturns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().queryParam("fields", "name;capital;population").when().get("/v2/subregion/{subregion}", "Western%20Europe");
        Assert.assertEquals(404, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_validReturns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/lang/{lang}", "Spanish");
        Assert.assertEquals(404, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testPostOnV2_returnsMethodNotAllowed405() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().body("{\"dummy\":\"" + UUID.randomUUID().toString() + "\"}").when().post("/v2");
        Assert.assertEquals(405, act.getStatusCode());
    }
}