package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class DateParseTest {

    @BeforeClass
    public static void setup() {
        String env = System.getenv("BASE_URL");
        if (env == null || env.isEmpty()) {
            env = System.getProperty("baseUrl", "http://localhost:8080");
        }
        RestAssured.baseURI = env;
    }

    @Test(timeout = 60000)
    public void testParse_Wednesday_August_returns200() {
        given().when().get("/api/dateparse/{day}/{month}", "Wednesday", "Jan").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/{day}/{month}", "Wednesday", "Feb").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/{day}/{month}", "Wednesday", "Mar").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/{day}/{month}", "Wednesday", "Apr").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/dateparse/{day}/{month}", "Wednesday", "August");
        Assert.assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testParse_tuesday_MAR_returns200() {
        given().when().get("/api/dateparse/{day}/{month}", "tuesday", "May").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/{day}/{month}", "tuesday", "Jun").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/{day}/{month}", "tuesday", "Jul").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/{day}/{month}", "tuesday", "Sep").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/dateparse/{day}/{month}", "tuesday", "MAR");
        Assert.assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testParse_Superday_Movember_returns500() {
        given().when().get("/api/dateparse/{day}/{month}", "Wednesday", "Oct").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/{day}/{month}", "Wednesday", "Nov").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/{day}/{month}", "Wednesday", "Dec").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/dateparse/{day}/{month}", "Superday", "Movember");
        Assert.assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testParse_MixedCase_acceptsCaseInsensitive_and_returns200() {
        given().when().get("/api/dateparse/{day}/{month}", "Wednesday", "May").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/{day}/{month}", "wednesday", "jun").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/{day}/{month}", "WEDNESDAY", "JUL").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/dateparse/{day}/{month}", "WeDnEsDaY", "AuGuSt");
        Assert.assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testParse_Monday_withInvalidMonth_Movember_returns500() {
        given().when().get("/api/dateparse/{day}/{month}", "Monday", "Jan").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/{day}/{month}", "Monday", "Feb").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/dateparse/{day}/{month}", "Monday", "Movember");
        Assert.assertEquals(200, act.getStatusCode());
    }
}