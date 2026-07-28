package ts01gpt_5_mini;

import io.restassured.response.Response;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class PatTest {

    private static final String BASE = System.getProperty("baseUrl", System.getenv("BASE_URL")) != null
            ? System.getProperty("baseUrl", System.getenv("BASE_URL"))
            : "http://localhost:8080";

    @Test(timeout = 60000)
    public void test_pat_short_length_returns_0() {
        given().when().get(BASE + "/api/pat/{txt}", "healthcheck").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/pat/{txt}/{pat}", "irrelevanttext", "ab");
        act.then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void test_pat_found_returns_1() {
        given().when().get(BASE + "/api/pat/{txt}", "ping").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/pat/{txt}/{pat}", "xxabcxx", "abc");
        act.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void test_reverse_found_returns_2() {
        given().when().get(BASE + "/api/pat/{txt}", "ping").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/pat/{txt}/{pat}", "xxcbaxx", "abc");
        act.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void test_pat_and_reverse_contiguous_returns_index() {
        given().when().get(BASE + "/api/pat/{txt}", "ping").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/pat/{txt}/{pat}", "xxabccbayy", "abc");
        act.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void test_reverse_first_then_pat_noncontiguous_returns_index() {
        given().when().get(BASE + "/api/pat/{txt}", "ping").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/pat/{txt}/{pat}", "xxcbaXXabcYY", "abc");
        act.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void test_patlen_gt2_none_found_returns_0() {
        given().when().get(BASE + "/api/pat/{txt}", "ping").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/pat/{txt}/{pat}", "zzzzzz", "abc");
        act.then().body(equalTo("0"));
    }
}