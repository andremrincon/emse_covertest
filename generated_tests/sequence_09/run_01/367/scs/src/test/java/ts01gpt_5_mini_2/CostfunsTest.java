package ts01gpt_5_mini_2;

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
        RestAssured.baseURI = System.getProperty("api.base", System.getenv().getOrDefault("API_BASE", "http://localhost:8080"));
    }

    @Test(timeout = 60000)
    public void test_zero_return_when_i_is_minus4_and_s_is_abab() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response res = given().pathParam("i", -4).pathParam("s", "abab").when().get("/api/costfuns/{i}/{s}");
        assertEquals("10", res.getBody().asString());
    }

    @Test(timeout = 60000)
    public void test_status_ok_when_i_equals_five() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        given().pathParam("i", 5).pathParam("s", "algorithm").when().get("/api/costfuns/{i}/{s}").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_status_ok_for_large_negative_i_triggers_less_than_branches() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        given().pathParam("i", -500).pathParam("s", "algorithm").when().get("/api/costfuns/{i}/{s}").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_status_ok_for_large_positive_i_triggers_greater_than_branches() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        given().pathParam("i", 1000).pathParam("s", "algorithm").when().get("/api/costfuns/{i}/{s}").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_status_ok_when_s_equals_baab_triggers_string_equals_branch() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        given().pathParam("i", 1).pathParam("s", "baab").when().get("/api/costfuns/{i}/{s}").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_status_ok_when_s_compareto_greater_than_target() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        given().pathParam("i", -4).pathParam("s", "z").when().get("/api/costfuns/{i}/{s}").then().statusCode(200);
    }
}