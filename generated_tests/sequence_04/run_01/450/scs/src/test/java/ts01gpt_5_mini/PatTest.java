package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class PatTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void test_pat_length_two_returns_ok_status() {
        String arrangeTxt = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", arrangeTxt).then().statusCode(lessThan(300));
        given().when().get("/api/pat/{txt}/{pat}", "xyzabc", "ab").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_pat_occurs_only_status_ok() {
        String arrangeTxt = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", arrangeTxt).then().statusCode(lessThan(300));
        given().when().get("/api/pat/{txt}/{pat}", "xxABCyy", "ABC").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_reverse_occurs_only_status_ok() {
        String arrangeTxt = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", arrangeTxt).then().statusCode(lessThan(300));
        given().when().get("/api/pat/{txt}/{pat}", "xxCBAyy", "ABC").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_pat_and_reverse_non_adjacent_starting_with_pat_status_ok() {
        String arrangeTxt = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", arrangeTxt).then().statusCode(lessThan(300));
        given().when().get("/api/pat/{txt}/{pat}", "xxabcxxxcba", "abc").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_pat_and_reverse_adjacent_starting_with_pat_status_ok() {
        String arrangeTxt = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", arrangeTxt).then().statusCode(lessThan(300));
        given().when().get("/api/pat/{txt}/{pat}", "xxabccbayy", "abc").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_reverse_then_pat_non_adjacent_status_ok() {
        String arrangeTxt = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", arrangeTxt).then().statusCode(lessThan(300));
        given().when().get("/api/pat/{txt}/{pat}", "xxcbaxxxabc", "abc").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_reverse_then_pat_adjacent_status_ok() {
        String arrangeTxt = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", arrangeTxt).then().statusCode(lessThan(300));
        given().when().get("/api/pat/{txt}/{pat}", "xxcbaabczz", "abc").then().statusCode(200);
    }

}