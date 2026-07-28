package ts01gpt_5_mini;

import org.junit.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class NotyPevarTest {
    private static final String BASE = System.getenv().getOrDefault("BASE_URL", System.getProperty("base.url", "http://localhost:8080"));

    @Test(timeout = 60000)
    public void test_notypevar_i28_executes_all_branches_and_returns_three() {
        given().when().get(BASE + "/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/notypevar/28/a");
        act.then().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void test_notypevar_i7_triggers_hello7_branch_and_returns_three() {
        given().when().get(BASE + "/api/pat/The").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/notypevar/7/a");
        act.then().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void test_notypevar_compareTo_branch_returns_two() {
        given().when().get(BASE + "/api/costfuns/1/algorithm").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/notypevar/2/z");
        act.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void test_notypevar_y_greater_than_x_returns_three() {
        given().when().get(BASE + "/api/filesuffix/%2Fhome%2Fuser%2Fdocuments/quarterly_report.docx").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/notypevar/6/a");
        act.then().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void test_notypevar_no_conditions_true_returns_zero() {
        given().when().get(BASE + "/api/title/male/Smith").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/notypevar/0/a");
        act.then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void test_notypevar_invalid_integer_returns_400() {
        given().when().get(BASE + "/api/pat/ABC").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/notypevar/abc/a").then().statusCode(400);
    }
}