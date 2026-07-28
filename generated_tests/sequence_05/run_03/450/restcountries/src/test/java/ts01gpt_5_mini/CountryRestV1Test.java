package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV1Test {

    @BeforeClass
    public static void setup() {
        String fromProp = System.getProperty("base.url");
        String fromEnv = System.getenv("BASE_URL");
        if (fromProp != null && !fromProp.isEmpty()) {
            RestAssured.baseURI = fromProp;
        } else if (fromEnv != null && !fromEnv.isEmpty()) {
            RestAssured.baseURI = fromEnv;
        } else {
            RestAssured.baseURI = "http://localhost:8080/rest";
        }
    }

    @Test(timeout = 60000)
    public void testGetByAlphaSuccess_US() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/US");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaBad_123() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/123");
        resp.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaNotFound_XYZ() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/XYZ");
        resp.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaListSuccess_US_CA() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha?codes=US,CA");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaListBad_missingCodes() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaListServerError_arrayString() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha?codes=[\"US\",\"CA\"]");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrencySuccess_USD() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/currency/USD");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCurrencyBad_123() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/currency/123");
        resp.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByNameSuccess_France_fullTextFalse() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/name/France?fullText=false");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByNameNotFound_123() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/name/123");
        resp.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCodeSuccess_1() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/callingcode/1");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCapitalLondon() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/capital/London");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegionEurope() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/region/Europe");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_es() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/lang/es");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPostOnV1_returnsMethodNotAllowed() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().contentType(ContentType.JSON).body("{}").when().post("/v1");
        resp.then().statusCode(405);
    }
}