package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class PatTest {

    private static String base;

    @BeforeClass
    public static void init() {
        String env = System.getenv("BASE_URL");
        if (env != null && !env.isEmpty()) {
            base = env;
        } else {
            base = System.getProperty("api.base", "http://localhost:8080");
        }
        if (base.endsWith("/")) {
            base = base.substring(0, base.length() - 1);
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void test_pat_not_found_returns_zero() {
        given().when().get(base + "/api/pat/{txt}", "health").then().statusCode(lessThan(300));
        String txt = "abcdefg";
        String pat = "xyz";
        given().when().get(base + "/api/pat/{txt}/{pat}", txt, pat).then().assertThat().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void test_pat_found_only_returns_one() {
        given().when().get(base + "/api/pat/{txt}", "ping").then().statusCode(lessThan(300));
        String txt = "xxpatternyy";
        String pat = "pattern";
        given().when().get(base + "/api/pat/{txt}/{pat}", txt, pat).then().assertThat().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void test_reverse_found_only_returns_two() {
        given().when().get(base + "/api/pat/{txt}", "ping2").then().statusCode(lessThan(300));
        String txt = "xxnrettapyy";
        String pat = "pattern";
        given().when().get(base + "/api/pat/{txt}/{pat}", txt, pat).then().assertThat().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void test_both_found_nonadjacent_pat_first_returns_index() {
        given().when().get(base + "/api/pat/{txt}", "setup").then().statusCode(lessThan(300));
        String txt = "xxpatternyyxxnrettap";
        String pat = "pattern";
        given().when().get(base + "/api/pat/{txt}/{pat}", txt, pat).then().assertThat().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void test_both_found_adjacent_pat_then_reverse_returns_index() {
        given().when().get(base + "/api/pat/{txt}", "ready").then().statusCode(lessThan(300));
        String txt = "prepatternnrettap";
        String pat = "pattern";
        given().when().get(base + "/api/pat/{txt}/{pat}", txt, pat).then().assertThat().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void test_reverse_then_pat_adjacent_returns_index_of_reverse() {
        given().when().get(base + "/api/pat/{txt}", "check").then().statusCode(lessThan(300));
        String txt = "nrettappattern";
        String pat = "pattern";
        given().when().get(base + "/api/pat/{txt}/{pat}", txt, pat).then().assertThat().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void test_reverse_then_pat_nonadjacent_returns_index_of_reverse() {
        given().when().get(base + "/api/pat/{txt}", "warm").then().statusCode(lessThan(300));
        String txt = "xnrettapxxpattern";
        String pat = "pattern";
        given().when().get(base + "/api/pat/{txt}/{pat}", txt, pat).then().assertThat().body(equalTo("1"));
    }
}