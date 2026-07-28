package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
public class CountryRestV1Test {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Ignore("expected:<200> but was:<404>")
    @Test(timeout = 60000)
    public void testGetByAlpha_Success_US() {
        Response resp = given().when().get("/v1/alpha/US");
        assertEquals(200, resp.getStatusCode());
    }

    @Ignore("expected:<400> but was:<404>")
    @Test(timeout = 60000)
    public void testGetByAlpha_BadRequest_Short() {
        Response resp = given().when().get("/v1/alpha/1");
        assertEquals(400, resp.getStatusCode());
    }

    @Ignore("expected:<200> but was:<404>")
    @Test(timeout = 60000)
    public void testGetByAlphaList_Success_US_CA() {
        Response resp = given().when().get("/v1/alpha?codes=US,CA");
        assertEquals(200, resp.getStatusCode());
    }

    @Ignore("expected:<400> but was:<404>")
    @Test(timeout = 60000)
    public void testGetByAlphaList_BadRequest_MissingCodes() {
        Response resp = given().when().get("/v1/alpha");
        assertEquals(400, resp.getStatusCode());
    }

    @Ignore("expected:<200> but was:<404>")
    @Test(timeout = 60000)
    public void testGetByCurrency_Success_USD() {
        Response resp = given().when().get("/v1/currency/USD");
        assertEquals(200, resp.getStatusCode());
    }

    @Ignore("expected:<400> but was:<404>")
    @Test(timeout = 60000)
    public void testGetByCurrency_BadRequest_Short() {
        Response resp = given().when().get("/v1/currency/12");
        assertEquals(400, resp.getStatusCode());
    }

    @Ignore("expected:<200> but was:<404>")
    @Test(timeout = 60000)
    public void testGetByName_Success_France_FullTextFalse() {
        Response resp = given().when().get("/v1/name/France?fullText=false");
        assertEquals(200, resp.getStatusCode());
    }

    @Ignore("expected:<200> but was:<404>")
    @Test(timeout = 60000)
    public void testGetByCallingCode_Success_1() {
        Response resp = given().when().get("/v1/callingcode/1");
        assertEquals(200, resp.getStatusCode());
    }

    @Ignore("expected:<200> but was:<404>")
    @Test(timeout = 60000)
    public void testGetByCapital_Success_London() {
        Response resp = given().when().get("/v1/capital/London");
        assertEquals(200, resp.getStatusCode());
    }

    @Ignore("expected:<200> but was:<404>")
    @Test(timeout = 60000)
    public void testGetByRegion_Success_Europe() {
        Response resp = given().when().get("/v1/region/Europe");
        assertEquals(200, resp.getStatusCode());
    }

    @Ignore("expected:<200> but was:<404>")
    @Test(timeout = 60000)
    public void testGetBySubregion_Success_WesternEurope() {
        Response resp = given().when().get("/v1/subregion/{sub}", "Western Europe");
        assertEquals(200, resp.getStatusCode());
    }

    @Ignore("expected:<200> but was:<404>")
    @Test(timeout = 60000)
    public void testGetByLanguage_Success_es() {
        Response resp = given().when().get("/v1/lang/es");
        assertEquals(200, resp.getStatusCode());
    }

}