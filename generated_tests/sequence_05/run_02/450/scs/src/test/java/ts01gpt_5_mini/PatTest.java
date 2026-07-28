package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class PatTest {

    @BeforeClass
    public static void init() {
        String env = System.getenv("BASE_URL");
        if (env != null && !env.isEmpty()) {
            baseURI = env;
        } else {
            baseURI = System.getProperty("api.base", "http://localhost:8080");
        }
    }

    @Test(timeout = 60000)
    public void testShortPattern_returns0() {
        given().when().get("/api/pat/{txt}", "sample").then().statusCode(lessThan(300));
        given().when().get("/api/pat/{txt}/{pat}", "hello world", "ab").then().assertThat().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatternOnly_returns1() {
        given().when().get("/api/pat/{txt}", "probe").then().statusCode(lessThan(300));
        given().when().get("/api/pat/{txt}/{pat}", "xxabcxx", "abc").then().assertThat().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testReverseOnly_returns2() {
        given().when().get("/api/pat/{txt}", "probe2").then().statusCode(lessThan(300));
        given().when().get("/api/pat/{txt}/{pat}", "xxcbaxx", "abc").then().assertThat().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testPatImmediatelyFollowedByReverse_returnsIndex() {
        given().when().get("/api/pat/{txt}", "setup").then().statusCode(lessThan(300));
        given().when().get("/api/pat/{txt}/{pat}", "xxabccbayy", "abc").then().assertThat().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testReverseImmediatelyFollowedByPat_returnsIndex() {
        given().when().get("/api/pat/{txt}", "setup2").then().statusCode(lessThan(300));
        given().when().get("/api/pat/{txt}/{pat}", "xxcbaabcyy", "abc").then().assertThat().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testBothNonAdjacent_returnsIndex() {
        given().when().get("/api/pat/{txt}", "setup3").then().statusCode(lessThan(300));
        given().when().get("/api/pat/{txt}/{pat}", "xxabcxxxcba", "abc").then().assertThat().body(equalTo("2"));
    }
}