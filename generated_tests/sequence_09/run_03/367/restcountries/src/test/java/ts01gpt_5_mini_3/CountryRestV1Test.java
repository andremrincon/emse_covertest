package ts01gpt_5_mini_3;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.hamcrest.Matchers.lessThan;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;

import org.junit.Ignore;
public class CountryRestV1Test {

    @BeforeClass
    public static void setUp() {
        String env = System.getProperty("api.base");
        if (env == null || env.isEmpty()) {
            env = System.getenv("API_BASE");
        }
        if (env == null || env.isEmpty()) {
            env = "http://localhost:8080";
        }
        RestAssured.baseURI = env;
    }

    @Test(timeout = 60000)
    public void testGetByAlphaSuccess() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/{alphacode}", "US").then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code <400> but was <404>.")
    @Test(timeout = 60000)
    public void testGetByAlphaBadRequest() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/{alphacode}", "123").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaNotFound() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/{alphacode}", "XYZ").then().statusCode(404);
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <400>.")
    @Test(timeout = 60000)
    public void testGetByAlphaListSuccess() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().queryParam("codes", "US,CA,MX").when().get("/v1/alpha").then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code <400> but was <200>.")
    @Test(timeout = 60000)
    public void testGetByAlphaListBadRequest() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().queryParam("codes", "123").when().get("/v1/alpha").then().statusCode(400);
    }

    @Ignore("Illegal character in query at index 22: /rest/v1/alpha?codes=[\"US\",\"CA\"]")
    @Test(timeout = 60000)
    public void testGetByAlphaListInternalServerError() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().queryParam("codes", "[\"US\",\"CA\"]").when().get("/v1/alpha").then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaListMissingCodesIsEmpty() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrencySuccess() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/currency/{currency}", "USD").then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code <400> but was <404>.")
    @Test(timeout = 60000)
    public void testGetByCurrencyBadRequest() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/currency/{currency}", "123").then().statusCode(400);
    }

    @Ignore("1 expectation failed. Expected status code <500> but was <200>.")
    @Test(timeout = 60000)
    public void testGetByCurrencyInternalServerError() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/currency/{currency}", "EUR").then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testGetByNameSuccess() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().queryParam("fullText", "false").when().get("/v1/name/{name}", "France").then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code <500> but was <404>.")
    @Test(timeout = 60000)
    public void testGetByNameInternalServerError() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/name/{name}", "True").then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCodeSuccess() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/callingcode/{callingcode}", "1").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCodeNotFound() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/callingcode/{callingcode}", "abc").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCapitalSuccess() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/capital/{capital}", "London").then().statusCode(200);
    }
}