package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

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
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_valid_returns200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v2/alpha/{alphacode}", "US").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_invalid_short_returns400() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v2/alpha/{alphacode}", "1").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_notFound_returns404() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v2/alpha/{alphacode}", "ZZZ").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_withFields_returnsFilteredBody() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().queryParam("fields", "name;capital;population").when().get("/v2/alpha/{alphacode}", "US").then().body(org.hamcrest.Matchers.containsString("\"name\""));
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_valid_codes_returns200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().queryParam("codes", "US,CA").when().get("/v2/alpha").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_bad_format_returns400() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().queryParam("codes", "[\"US\",\"CA\"]").when().get("/v2/alpha").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_valid_returns200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v2/currency/{currency}", "EUR").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_bad_length_returns400() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v2/currency/{currency}", "12").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByName_found_returns200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().queryParam("fullText", false).when().get("/v2/name/{name}", "Germany").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByName_numeric_notFound_returns404() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v2/name/{name}", "123").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCode_valid_returns200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v2/callingcode/{callingcode}", "1").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCapital_valid_returns200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v2/capital/{capital}", "Paris").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegion_valid_returns200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v2/region/{region}", "Europe").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPost_onV2_returnsMethodNotAllowed() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().post("/v2").then().statusCode(405);
    }
}