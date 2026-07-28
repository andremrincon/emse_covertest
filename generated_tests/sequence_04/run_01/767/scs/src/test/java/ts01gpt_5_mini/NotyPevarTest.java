package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class NotyPevarTest {

    @BeforeClass
    public static void initBaseUri() {
        String base = System.getenv("API_BASE");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("api.base", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void test_sumEquals56_path_executes_and_returns_three() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "setup-" + uid).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", 28, "aaa");
        Assert.assertEquals("3", resp.asString());
    }

    @Test(timeout = 60000)
    public void test_hello7_branch_executes_and_final_overwritten_returns_three() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "setup-" + uid).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", 7, "zzz");
        Assert.assertEquals("3", resp.asString());
    }

    @Test(timeout = 60000)
    public void test_compareTo_less_than_branch_returns_two() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "setup-" + uid).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", 0, "world");
        Assert.assertEquals("2", resp.asString());
    }
}