package ts01gpt_5_mini;

import org.junit.Test;
import java.util.Optional;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.containsString;

public class CountryRestV2Test {

    private String base() {
        String prop = System.getProperty("api.base");
        String env = System.getenv("API_BASE");
        return Optional.ofNullable(prop).orElse(Optional.ofNullable(env).orElse("http://localhost:8080/rest"));
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_valid_returns200() {
        given().when().get(base() + "/v2").then().statusCode(lessThan(300));
        given().when().get(base() + "/v2/alpha/US").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_invalidShort_returns400() {
        given().when().get(base() + "/v2").then().statusCode(lessThan(300));
        given().when().get(base() + "/v2/alpha/1").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_nonexistent_returns404() {
        given().when().get(base() + "/v2").then().statusCode(lessThan(300));
        given().when().get(base() + "/v2/alpha/ZZ").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_withFields_bodyContainsName() {
        given().when().get(base() + "/v2").then().statusCode(lessThan(300));
        given().queryParam("fields", "name;capital;population").when().get(base() + "/v2/alpha/US").then().body(containsString("name"));
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_valid_returns200() {
        given().when().get(base() + "/v2").then().statusCode(lessThan(300));
        given().queryParam("codes", "US").when().get(base() + "/v2/alpha").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_invalidFormat_returns400() {
        given().when().get(base() + "/v2").then().statusCode(lessThan(300));
        given().queryParam("codes", "[\"US\",\"CA\"]").when().get(base() + "/v2/alpha").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_invalidLength_returns400() {
        given().when().get(base() + "/v2").then().statusCode(lessThan(300));
        given().when().get(base() + "/v2/currency/12").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_valid_returns200() {
        given().when().get(base() + "/v2").then().statusCode(lessThan(300));
        given().when().get(base() + "/v2/currency/EUR").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByName_fullTextFalse_returns200() {
        given().when().get(base() + "/v2").then().statusCode(lessThan(300));
        given().queryParam("fullText", "false").when().get(base() + "/v2/name/France").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCode_nonexistent_returns404() {
        given().when().get(base() + "/v2").then().statusCode(lessThan(300));
        given().when().get(base() + "/v2/callingcode/99999").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testPost_methodNotAllowed_returns405() {
        given().when().get(base() + "/v2").then().statusCode(lessThan(300));
        given().when().post(base() + "/v2").then().statusCode(405);
    }

    @Test(timeout = 60000)
    public void testGetAll_withFields_bodyContainsName() {
        given().when().get(base() + "/v2").then().statusCode(lessThan(300));
        given().queryParam("fields", "name;capital;population").when().get(base() + "/v2").then().body(containsString("name"));
    }

    @Test(timeout = 60000)
    public void testGetAll_noFields_returns200() {
        given().when().get(base() + "/v2").then().statusCode(lessThan(300));
        given().when().get(base() + "/v2/all").then().statusCode(200);
    }
}