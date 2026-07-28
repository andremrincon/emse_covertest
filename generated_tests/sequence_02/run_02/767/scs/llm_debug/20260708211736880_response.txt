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
    private static final String BASE = System.getProperty("base.url",
            System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080");

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = BASE;
    }

    @Test(timeout = 60000)
    public void test_i28_results_in_3_via_overridden_branches() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uuid).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/notypevar/{i}/{s}", 28, "aaa");
        assertEquals("3", act.asString());
    }

    @Test(timeout = 60000)
    public void test_i3_with_s_greater_than_hello_returns_2() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uuid).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/notypevar/{i}/{s}", 3, "world");
        assertEquals("2", act.asString());
    }

    @Test(timeout = 60000)
    public void test_i7_triggers_hello_concat_branch_and_final_overwrite_returns_3() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uuid).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/notypevar/{i}/{s}", 7, "aaa");
        assertEquals("3", act.asString());
    }
}