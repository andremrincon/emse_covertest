package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import java.net.URLEncoder;
import java.io.UnsupportedEncodingException;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class RegexTest {

    private static String BASE;

    @BeforeClass
    public static void init() {
        String prop = System.getProperty("baseUrl");
        String env = System.getenv("BASE_URL");
        if (prop != null && !prop.isEmpty()) {
            BASE = prop;
        } else if (env != null && !env.isEmpty()) {
            BASE = env;
        } else {
            BASE = "http://localhost:8080";
        }
    }

    private String enc(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test(timeout = 60000)
    public void testUrlSubject() {
        given().when().get(BASE + "/api/pat/" + enc("The quick brown fox jumps over the lazy dog.")).then().statusCode(lessThan(300));
        String txt = "http://abc/def";
        given().when().get(BASE + "/api/pat/" + enc(txt)).then().statusCode(200).body(equalTo("none"));
    }

    @Test(timeout = 60000)
    public void testDateSubject() {
        given().when().get(BASE + "/api/pat/" + enc("The quick brown fox jumps over the lazy dog.")).then().statusCode(lessThan(300));
        String txt = "mon01jan";
        given().when().get(BASE + "/api/pat/" + enc(txt)).then().statusCode(200).body(equalTo("date"));
    }

    @Test(timeout = 60000)
    public void testFpeSubject() {
        given().when().get(BASE + "/api/pat/" + enc("The quick brown fox jumps over the lazy dog.")).then().statusCode(lessThan(300));
        String txt = "1.2e+34";
        given().when().get(BASE + "/api/pat/" + enc(txt)).then().statusCode(200).body(equalTo("none"));
    }

    @Test(timeout = 60000)
    public void testNoneSubject() {
        given().when().get(BASE + "/api/pat/" + enc("The quick brown fox jumps over the lazy dog.")).then().statusCode(lessThan(300));
        String txt = "hello";
        given().when().get(BASE + "/api/pat/" + enc(txt)).then().statusCode(200).body(equalTo("none"));
    }
}