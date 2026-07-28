package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PatTest {

    @BeforeClass
    public static void init() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("base.url", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void test_pat_shortPattern_returns_200() {
        given().when().get("/api/pat/{txt}", "TheQuickBrownFox").then().statusCode(lessThan(300));
        given().when().get("/api/pat/{txt}/{pat}", "sampletext", "ab").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_pat_pattern_found_returns_body_1() {
        given().when().get("/api/pat/{txt}", "TheQuickBrownFox").then().statusCode(lessThan(300));
        given().when().get("/api/pat/{txt}/{pat}", "xxPATyy", "PAT").then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void test_pat_reverse_found_returns_body_2() {
        given().when().get("/api/pat/{txt}", "TheQuickBrownFox").then().statusCode(lessThan(300));
        given().when().get("/api/pat/{txt}/{pat}", "zzCBAzz", "ABC").then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void test_pat_both_nonadjacent_returns_index_0() {
        given().when().get("/api/pat/{txt}", "TheQuickBrownFox").then().statusCode(lessThan(300));
        given().when().get("/api/pat/{txt}/{pat}", "ABCxxxCBA", "ABC").then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void test_pat_both_adjacent_returns_index_0() {
        given().when().get("/api/pat/{txt}", "TheQuickBrownFox").then().statusCode(lessThan(300));
        given().when().get("/api/pat/{txt}/{pat}", "ABCCBA", "ABC").then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void test_pat_reverse_first_adjacent_returns_index_0() {
        given().when().get("/api/pat/{txt}", "TheQuickBrownFox").then().statusCode(lessThan(300));
        given().when().get("/api/pat/{txt}/{pat}", "CBAABC", "ABC").then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void test_pat_no_match_returns_body_0() {
        given().when().get("/api/pat/{txt}", "TheQuickBrownFox").then().statusCode(lessThan(300));
        given().when().get("/api/pat/{txt}/{pat}", "abcdefghijk", "XYZ").then().body(equalTo("0"));
    }
}