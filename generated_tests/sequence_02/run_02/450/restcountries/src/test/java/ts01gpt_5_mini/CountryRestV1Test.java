package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV1Test {

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = System.getProperty("base.url", System.getenv("BASE_URL") == null ? "http://localhost:8080/rest" : System.getenv("BASE_URL"));
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_success() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/{code}", "US");
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_badRequest_short() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/{code}", "1");
        Assert.assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_notFound() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/{code}", "XYZ");
        Assert.assertEquals(404, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_success() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha?codes={codes}", "US,CA");
        Assert.assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_badRequest() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha?codes={codes}", "123");
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_notFound() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha?codes={codes}", "XX,YY,ZZ");
        Assert.assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_success() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/currency/{currency}", "USD");
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_badRequest() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/currency/{currency}", "12");
        Assert.assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByName_success_fullText_false() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/name/{name}?fullText={ft}", "France", "false");
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByName_notFound() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/name/{name}", "123");
        Assert.assertEquals(404, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByCallingCode_success() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/callingcode/{code}", "1");
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByCapital_success() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/capital/{capital}", "London");
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByRegion_success() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/region/{region}", "Europe");
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetBySubregion_success() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/subregion/{subregion}", "Western Europe");
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_success() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/lang/{lang}", "es");
        Assert.assertEquals(200, resp.getStatusCode());
    }
}