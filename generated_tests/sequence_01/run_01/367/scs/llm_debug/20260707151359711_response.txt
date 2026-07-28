package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class NotyPevarTest {

    private static final String BASE;
    static {
        String b = System.getProperty("api.base");
        if (b == null || b.isEmpty()) {
            b = System.getenv("API_BASE_URL");
        }
        if (b == null || b.isEmpty()) {
            b = "http://localhost:8080";
        }
        BASE = b;
    }

    @BeforeClass
    public static void setUpClass() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test(timeout = 60000)
    public void test_notypevar_i28_triggers_branches_and_returns_200() {
        given().when().get(BASE + "/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/notypevar/28/aaa");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_notypevar_i7_results_in_body_3() {
        given().when().get(BASE + "/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/notypevar/7/x");
        act.then().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void test_notypevar_i3_s_zzzz_results_in_200() {
        given().when().get(BASE + "/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/notypevar/3/zzzz");
        act.then().statusCode(200);
    }
}