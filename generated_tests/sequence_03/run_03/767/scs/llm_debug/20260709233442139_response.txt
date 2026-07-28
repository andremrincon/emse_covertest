package ts01gpt_5_mini;

import org.junit.Test;
import java.net.URLEncoder;
import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.lessThan;

public class RegexTest {

    private String base() {
        String b = System.getProperty("baseUrl");
        if (b != null && !b.isEmpty()) return b;
        String e = System.getenv("BASE_URL");
        if (e != null && !e.isEmpty()) return e;
        return "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testUrlPattern_returns200() throws Exception {
        String base = base();
        String health = URLEncoder.encode("The quick brown fox jumps over the lazy dog.", "UTF-8");
        given().when().get(base + "/api/pat/" + health).then().statusCode(lessThan(300));
        String txt = URLEncoder.encode("http://abc/def", "UTF-8");
        Response act = given().when().get(base + "/api/pat/" + txt);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDatePattern_returns200() throws Exception {
        String base = base();
        String health = URLEncoder.encode("The quick brown fox jumps over the lazy dog.", "UTF-8");
        given().when().get(base + "/api/pat/" + health).then().statusCode(lessThan(300));
        String txt = URLEncoder.encode("mon12jan", "UTF-8");
        Response act = given().when().get(base + "/api/pat/" + txt);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFpePattern_returns200() throws Exception {
        String base = base();
        String health = URLEncoder.encode("The quick brown fox jumps over the lazy dog.", "UTF-8");
        given().when().get(base + "/api/pat/" + health).then().statusCode(lessThan(300));
        String txt = URLEncoder.encode("12.3e+45", "UTF-8");
        Response act = given().when().get(base + "/api/pat/" + txt);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNonePattern_returns200() throws Exception {
        String base = base();
        String health = URLEncoder.encode("The quick brown fox jumps over the lazy dog.", "UTF-8");
        given().when().get(base + "/api/pat/" + health).then().statusCode(lessThan(300));
        String txt = URLEncoder.encode("hello", "UTF-8");
        Response act = given().when().get(base + "/api/pat/" + txt);
        act.then().statusCode(200);
    }
}