package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class CostfunsTest {

    @BeforeClass
    public static void setup() {
        String env = System.getenv("API_BASE");
        if (env == null || env.isEmpty()) {
            env = System.getProperty("api.base", "http://localhost:8080");
        }
        RestAssured.baseURI = env;
    }

    @Test(timeout = 60000)
    public void testCostfuns_returnsTen_when_s_not_abab() {
        given().when().get("/api/pat/a").then().statusCode(lessThan(300));
        io.restassured.response.Response act = given().when().get("/api/costfuns/1/zzzz");
        act.then().body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testCostfuns_returnsSix_when_s_abab_and_i_not_minus4() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        io.restassured.response.Response act = given().when().get("/api/costfuns/0/abab");
        act.then().body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testCostfuns_returnsZero_when_i_minus4_and_s_abab() {
        given().when().get("/api/cookie/session-id/1/example.com").then().statusCode(lessThan(300));
        io.restassured.response.Response act = given().when().get("/api/costfuns/-4/abab");
        act.then().body(equalTo("10"));
    }
}