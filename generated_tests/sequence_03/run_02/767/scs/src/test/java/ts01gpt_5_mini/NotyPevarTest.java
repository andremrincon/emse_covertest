package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class NotyPevarTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null) base = System.getenv("API_BASE");
        if (base == null) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void test_notypevar_returns200_for_zero_input() {
        String a1 = UUID.randomUUID().toString();
        given().when().get("/api/text2txt/{w}/{x}/{y}", a1, "quick", "brown").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", 0, "aaa");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void test_notypevar_returns200_when_compareTo_branch_triggered() {
        String a1 = UUID.randomUUID().toString();
        given().when().get("/api/text2txt/{w}/{x}/{y}", "The", a1, "brown").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", 1, "z");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void test_notypevar_returns200_when_sum_equals_56() {
        String a1 = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", a1).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", 28, "zzzz");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void test_notypevar_returns200_when_hello7_branch_executed() {
        String a1 = UUID.randomUUID().toString();
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "add", "1", "2").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", 7, "hello");
        assertEquals(200, resp.getStatusCode());
    }
}