package ts01gpt_5_mini;

import org.junit.Test;
import org.junit.Assert;
import static org.hamcrest.Matchers.lessThan;
import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

public class PatTest {

    private static String base() {
        String b = System.getProperty("baseUrl");
        if (b == null || b.isEmpty()) {
            b = System.getenv("BASE_URL");
        }
        if (b == null || b.isEmpty()) {
            b = "http://localhost:8080";
        }
        return b;
    }

    @Test(timeout = 60000)
    public void test_pat_length_two_returns_zero() {
        String base = base();
        given().when().get(base + "/api/pat/{txt}", "arrange1").then().statusCode(lessThan(300));
        Response r = given().when().get(base + "/api/pat/{txt}/{pat}", "abc", "ab");
        Assert.assertEquals("0", r.getBody().asString());
    }

    @Test(timeout = 60000)
    public void test_pat_present_returns_one() {
        String base = base();
        given().when().get(base + "/api/pat/{txt}", "arrange2").then().statusCode(lessThan(300));
        Response r = given().when().get(base + "/api/pat/{txt}/{pat}", "xxabcxx", "abc");
        Assert.assertEquals("1", r.getBody().asString());
    }

    @Test(timeout = 60000)
    public void test_reverse_present_returns_two() {
        String base = base();
        given().when().get(base + "/api/pat/{txt}", "arrange3").then().statusCode(lessThan(300));
        Response r = given().when().get(base + "/api/pat/{txt}/{pat}", "xxcba", "abc");
        Assert.assertEquals("2", r.getBody().asString());
    }

    @Test(timeout = 60000)
    public void test_pat_and_reverse_noncontiguous_returns_index_of_first() {
        String base = base();
        given().when().get(base + "/api/pat/{txt}", "arrange4").then().statusCode(lessThan(300));
        Response r = given().when().get(base + "/api/pat/{txt}/{pat}", "abcxxcba", "abc");
        Assert.assertEquals("0", r.getBody().asString());
    }

    @Test(timeout = 60000)
    public void test_pat_followed_immediately_by_reverse_returns_index() {
        String base = base();
        given().when().get(base + "/api/pat/{txt}", "arrange5").then().statusCode(lessThan(300));
        Response r = given().when().get(base + "/api/pat/{txt}/{pat}", "abccba", "abc");
        Assert.assertEquals("0", r.getBody().asString());
    }

    @Test(timeout = 60000)
    public void test_reverse_followed_immediately_by_pat_returns_index() {
        String base = base();
        given().when().get(base + "/api/pat/{txt}", "arrange6").then().statusCode(lessThan(300));
        Response r = given().when().get(base + "/api/pat/{txt}/{pat}", "cbaabc", "abc");
        Assert.assertEquals("0", r.getBody().asString());
    }

}