package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import org.junit.Assert;

public class NotyPevarTest {

    @BeforeClass
    public static void setUp() {
        String cfg = System.getProperty("base.url");
        if (cfg == null || cfg.isEmpty()) cfg = System.getenv("BASE_URL");
        if (cfg == null || cfg.isEmpty()) cfg = "http://localhost:8080";
        RestAssured.baseURI = cfg;
    }

    @Test(timeout = 60000)
    public void test_notypevar_i28_returns_200() {
        given().when().get("/api/pat/{txt}", UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", 28, "aaa");
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void test_notypevar_i7_body_is_3() {
        given().when().get("/api/pat/{txt}", UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", 7, "abc");
        Assert.assertEquals("3", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void test_notypevar_i5_s_greater_than_hello_body_is_2() {
        given().when().get("/api/pat/{txt}", UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", 5, "world");
        Assert.assertEquals("2", resp.getBody().asString());
    }
}