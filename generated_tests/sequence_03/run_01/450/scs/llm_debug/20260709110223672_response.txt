package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class PatTest {

    @BeforeClass
    public static void init() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testShortPatternReturnsZero() {
        given().when().get("/api/pat/{txt}", "warmup").then().statusCode(lessThan(300));
        String txt = "anytext" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}/{pat}", txt, "ab").then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatternFoundReturnsOne() {
        given().when().get("/api/pat/{txt}", "warmup2").then().statusCode(lessThan(300));
        String txt = "zzabczz" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}/{pat}", txt, "abc").then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testReverseOnlyReturnsTwo() {
        given().when().get("/api/pat/{txt}", "warmup3").then().statusCode(lessThan(300));
        String txt = "xxcbaxx" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}/{pat}", txt, "abc").then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testBothNonAdjacentReturnsIndex() {
        given().when().get("/api/pat/{txt}", "warmup4").then().statusCode(lessThan(300));
        String txt = "zzabczzcba" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}/{pat}", txt, "abc").then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testPatFollowedByReverseAdjacentReturnsIndex() {
        given().when().get("/api/pat/{txt}", "warmup5").then().statusCode(lessThan(300));
        String txt = "xxabccbayy" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}/{pat}", txt, "abc").then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testReverseFollowedByPatAdjacentReturnsIndex() {
        given().when().get("/api/pat/{txt}", "warmup6").then().statusCode(lessThan(300));
        String txt = "xxcbaabcyy" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}/{pat}", txt, "abc").then().body(equalTo("2"));
    }
}