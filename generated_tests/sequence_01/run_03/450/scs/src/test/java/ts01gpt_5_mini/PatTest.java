package ts01gpt_5_mini;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class PatTest {

    private static final String BASE = System.getenv().getOrDefault("BASE_URL", System.getProperty("api.base", "http://localhost:8080"));

    private static String enc(String s) {
        try {
            return java.net.URLEncoder.encode(s, "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test(timeout = 60000)
    public void testPatLengthTwoReturnsZero() {
        given().when().get(BASE + "/api/pat/" + enc("health")).then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/pat/" + enc("some text") + "/" + enc("ab")).then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatFoundReturnsOne() {
        given().when().get(BASE + "/api/pat/" + enc("ready")).then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/pat/" + enc("xxxabcxxx") + "/" + enc("abc")).then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testReverseFoundReturnsTwo() {
        given().when().get(BASE + "/api/pat/" + enc("ping")).then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/pat/" + enc("xxxcbaaxx") + "/" + enc("abc")).then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testPatFollowedByReverseAdjacentReturnsIndex() {
        given().when().get(BASE + "/api/pat/" + enc("init")).then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/pat/" + enc("abccba") + "/" + enc("abc")).then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatFollowedByReverseNonAdjacentReturnsIndex() {
        given().when().get(BASE + "/api/pat/" + enc("start")).then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/pat/" + enc("abcxxxcba") + "/" + enc("abc")).then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testReverseFollowedByPatAdjacentReturnsIndex() {
        given().when().get(BASE + "/api/pat/" + enc("check")).then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/pat/" + enc("cbaabc") + "/" + enc("abc")).then().body(equalTo("0"));
    }
}