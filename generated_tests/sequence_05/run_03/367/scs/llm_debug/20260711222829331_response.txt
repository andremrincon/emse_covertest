package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import java.net.URLEncoder;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class RegexTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testUrlPatternTriggersSuccess() throws Exception {
        String seed = URLEncoder.encode("seed", "UTF-8");
        given().when().get("/api/pat/{txt}", seed).then().statusCode(lessThan(300));
        String txt = URLEncoder.encode("http://abc/def", "UTF-8");
        given().when().get("/api/pat/{txt}", txt).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDatePatternTriggersSuccess() throws Exception {
        String seed = URLEncoder.encode("setup", "UTF-8");
        given().when().get("/api/pat/{txt}", seed).then().statusCode(lessThan(300));
        String txt = URLEncoder.encode("mon12jan", "UTF-8");
        given().when().get("/api/pat/{txt}", txt).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFloatingExponentPatternTriggersSuccess() throws Exception {
        String seed = URLEncoder.encode("init", "UTF-8");
        given().when().get("/api/pat/{txt}", seed).then().statusCode(lessThan(300));
        String txt = URLEncoder.encode("1.2e+34", "UTF-8");
        given().when().get("/api/pat/{txt}", txt).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNonePatternReturnsSuccess() throws Exception {
        String seed = URLEncoder.encode("prime", "UTF-8");
        given().when().get("/api/pat/{txt}", seed).then().statusCode(lessThan(300));
        String txt = URLEncoder.encode("foobar", "UTF-8");
        given().when().get("/api/pat/{txt}", txt).then().statusCode(200);
    }
}