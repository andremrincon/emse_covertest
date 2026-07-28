package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class CostfunsTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv().getOrDefault("API_BASE", System.getProperty("api.base", "http://localhost:8080"));
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testSubject_AllStringBranches_Final10() {
        given().when().get("/api/calc/add/1/1").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/5/baab");
        assertEquals("10", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testSubject_iLessThanMinus444_and_s_equals_abab() {
        given().when().get("/api/calc/add/2/3").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/-500/abab");
        assertEquals("10", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testSubject_iGreaterThan666_with_small_string_results() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/700/aaaaaa");
        assertEquals("10", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testSubject_compareTo_equal_case_triggers_ge_zero() {
        given().when().get("/api/pat/hello").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/555/ababba");
        assertEquals("10", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testSubject_iEqualsMinus4_disables_not_equal_branch_and_returns_ok_status() {
        given().when().get("/api/cookie/session-id/1/example.com").then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/-4/xyz").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubject_invalid_i_returns_400() {
        given().when().get("/api/calc/add/1/2").then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/one/test").then().statusCode(400);
    }
}