package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class NotyPevarTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void test_ReturnsZero_WhenNoConditionsMet() {
        given().when().get("/api/pat/{txt}", "arrange-zero").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", 0, "a");
        Assert.assertEquals("0", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void test_ReturnsTwo_WhenCompareToLessThanTrue() {
        given().when().get("/api/pat/{txt}", "arrange-compare").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", 5, "z");
        Assert.assertEquals("2", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void test_ExecutesXPlusYEquals56Branch_ForI28() {
        given().when().get("/api/pat/{txt}", "arrange-xplusy").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", 28, "ignore");
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void test_ExecutesXsPlusYEqualsHello7Branch_ForI7() {
        given().when().get("/api/pat/{txt}", "arrange-hello7").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", 7, "anything");
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void test_ReturnsThree_WhenYGreaterThanX() {
        given().when().get("/api/pat/{txt}", "arrange-ygtx").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", 6, "a");
        Assert.assertEquals("3", resp.getBody().asString());
    }
}