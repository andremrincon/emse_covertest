package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceBaseTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
            if (base == null || base.isEmpty()) {
                base = "http://localhost:8080/rest";
            }
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void test_v1_alpha_twoLetter_success() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/{alphacode}", "US");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_v2_alpha_threeLetter_success_with_fields() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response act = given().queryParam("fields", "name;capital;population").when().get("/v2/alpha/{alphacode}", "USA");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_v1_alpha_not_found_returns_404() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/{alphacode}", "XYZ");
        act.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void test_v2_alpha_codes_semicolon_list_returns_200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response act = given().queryParam("codes", "US;CA").when().get("/v2/alpha");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_v2_alpha_missing_codes_returns_400() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/alpha");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void test_v1_name_fulltext_exact_match_returns_200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().queryParam("fullText", "true").when().get("/v1/name/{name}", "France");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_v1_name_fulltext_altspelling_match_returns_200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().queryParam("fullText", "true").when().get("/v1/name/{name}", "Federal Republic of Germany");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_v1_name_substring_altspelling_contains_returns_200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/name/{name}", "Federal");
        act.then().statusCode(200);
    }
}