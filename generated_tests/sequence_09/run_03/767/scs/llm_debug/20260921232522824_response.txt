package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class DateParseTest {

    private static String BASE;

    @BeforeClass
    public static void setup() {
        String env = System.getProperty("baseUrl");
        if (env == null || env.isEmpty()) {
            env = System.getenv("BASE_URL");
        }
        if (env == null || env.isEmpty()) {
            env = "http://localhost:8080";
        }
        BASE = env;
        RestAssured.baseURI = BASE;
    }

    @Test(timeout = 60000)
    public void test_dayAbbreviationOnly_setsResultOne() {
        String setupTxt = "setup-" + UUID.randomUUID().toString();
        given().when().get(BASE + "/api/pat/{txt}", setupTxt).then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/dateparse/{dayname}/{monthname}", "mon", "unknown");
        assertEquals("1", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void test_monthAbbreviationOnly_returnsMonthValue() {
        String setupTxt = "setup-" + UUID.randomUUID().toString();
        given().when().get(BASE + "/api/pat/{txt}", setupTxt).then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/dateparse/{dayname}/{monthname}", "noday", "jan");
        assertEquals("1", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void test_dayAndMonthBothMatch_accumulatesValues() {
        String setupTxt = "setup-" + UUID.randomUUID().toString();
        given().when().get(BASE + "/api/pat/{txt}", setupTxt).then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/dateparse/{dayname}/{monthname}", "wed", "mar");
        assertEquals("4", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void test_neitherDayNorMonthMatches_returnsZero() {
        String setupTxt = "setup-" + UUID.randomUUID().toString();
        given().when().get(BASE + "/api/pat/{txt}", setupTxt).then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/dateparse/{dayname}/{monthname}", "unknownDay", "unknownMonth");
        assertEquals("0", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void test_sundayAndDecember_combinesToThirteen() {
        String setupTxt = "setup-" + UUID.randomUUID().toString();
        given().when().get(BASE + "/api/pat/{txt}", setupTxt).then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/dateparse/{dayname}/{monthname}", "sun", "dec");
        assertEquals("13", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void test_thurAndFebruary_returnsThree() {
        String setupTxt = "setup-" + UUID.randomUUID().toString();
        given().when().get(BASE + "/api/pat/{txt}", setupTxt).then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/dateparse/{dayname}/{monthname}", "thur", "feb");
        assertEquals("3", act.getBody().asString());
    }
}