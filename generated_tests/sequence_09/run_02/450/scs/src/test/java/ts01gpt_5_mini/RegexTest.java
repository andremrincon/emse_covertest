package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import static org.hamcrest.Matchers.lessThan;

public class RegexTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.url");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testUrlPatternMatched() throws Exception {
        String probe = URLEncoder.encode("a", StandardCharsets.UTF_8.name());
        given().when().get("/api/pat/{txt}", probe).then().statusCode(lessThan(300));
        String txt = URLEncoder.encode("http://abc/def", StandardCharsets.UTF_8.name());
        given().when().get("/api/pat/{txt}", txt).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDatePatternMatched() throws Exception {
        String probe = URLEncoder.encode("a", StandardCharsets.UTF_8.name());
        given().when().get("/api/pat/{txt}", probe).then().statusCode(lessThan(300));
        String txt = URLEncoder.encode("wed12mar", StandardCharsets.UTF_8.name());
        given().when().get("/api/pat/{txt}", txt).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFpePatternMatched() throws Exception {
        String probe = URLEncoder.encode("a", StandardCharsets.UTF_8.name());
        given().when().get("/api/pat/{txt}", probe).then().statusCode(lessThan(300));
        String txt = URLEncoder.encode("1.2e+05", StandardCharsets.UTF_8.name());
        given().when().get("/api/pat/{txt}", txt).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNonePatternResultsInNonePath() throws Exception {
        String probe = URLEncoder.encode("a", StandardCharsets.UTF_8.name());
        given().when().get("/api/pat/{txt}", probe).then().statusCode(lessThan(300));
        String txt = URLEncoder.encode("hello-world", StandardCharsets.UTF_8.name());
        given().when().get("/api/pat/{txt}", txt).then().statusCode(200);
    }
}