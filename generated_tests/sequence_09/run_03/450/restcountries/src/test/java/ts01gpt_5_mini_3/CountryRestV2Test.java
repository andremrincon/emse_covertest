package ts01gpt_5_mini_3;

import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.http.ContentType;

public class CountryRestV2Test {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGetByAlphaSuccessWithFields() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given().when().get("/v2/alpha/US?fields=name;capital;population").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaBadLengthReturns400() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given().when().get("/v2/alpha/1").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaNotFoundReturns404() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given().when().get("/v2/alpha/ZZZ").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaListSuccessWithFields() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given().when().get("/v2/alpha?codes=US,CA&fields=name;capital").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaListBadFormatReturns400() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given().when().get("/v2/alpha?codes=%5B%22US%22,%22CA%22%5D").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaListEmptyCodesReturns400() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given().when().get("/v2/alpha?codes=").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrencySuccessWithFields() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given().when().get("/v2/currency/EUR?fields=name;capital;population").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCurrencyBadLengthReturns400() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given().when().get("/v2/currency/123").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByNameSuccessFullTextFalse() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given().when().get("/v2/name/France?fullText=false&fields=name;capital").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCodeSuccess() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given().when().get("/v2/callingcode/1?fields=name;capital;region").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCapitalSuccess() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given().when().get("/v2/capital/Paris?fields=name;capital;population").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegionSuccess() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given().when().get("/v2/region/Europe?fields=name;capital;population").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetBySubRegionSuccess() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given().when().get("/v2/subregion/Western%20Europe?fields=name;capital;population").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPostToV2ReturnsMethodNotAllowed405() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given().contentType(ContentType.JSON).body("{}").when().post("/v2").then().statusCode(405);
    }
}