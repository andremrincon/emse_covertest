package ts01gpt_5_mini_3;

import io.restassured.response.Response;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class NotyPevarTest {

    private static final String BASE;
    static {
        String b = System.getProperty("api.baseUrl");
        if (b == null || b.isEmpty()) b = System.getenv("API_BASE_URL");
        if (b == null || b.isEmpty()) b = System.getenv("BASE_URL");
        if (b == null || b.isEmpty()) b = "http://localhost:8080";
        BASE = b;
    }

    @Test(timeout = 60000)
    public void test_sumEquals56_producesResult28() {
        given().when().get(BASE + "/api/pat/a").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/notypevar/28/example-string");
        act.then().statusCode(200).assertThat().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void test_intermediateHello7_branch_executes_status200() {
        given().when().get(BASE + "/api/pat/The").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/notypevar/7/aaa").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_compareTo_lessThan_returns2() {
        given().when().get(BASE + "/api/pat/a").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/notypevar/1/zzz");
        act.then().statusCode(200).assertThat().body(equalTo("2"));
    }
}