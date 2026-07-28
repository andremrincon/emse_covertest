package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class PatTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testPatLengthTwoReturnsZero() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "setup-" + uuid).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", "some text here", "ab");
        assertEquals("0", resp.asString());
    }

    @Test(timeout = 60000)
    public void testPatFoundWithoutReverseReturnsOne() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "setup-" + uuid).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", "ABABCABAB", "ABAB");
        assertEquals("1", resp.asString());
    }

    @Test(timeout = 60000)
    public void testReverseFoundWithoutPatReturnsTwo() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "setup-" + uuid).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", "BABA", "ABAB");
        assertEquals("2", resp.asString());
    }

    @Test(timeout = 60000)
    public void testPatFollowedImmediatelyByReverseReturnsIndexZero() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "setup-" + uuid).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", "ABAABA", "ABA");
        assertEquals("0", resp.asString());
    }

    @Test(timeout = 60000)
    public void testReverseFollowedImmediatelyByPatReturnsIndexZero() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "setup-" + uuid).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", "DCBAABCD", "ABCD");
        assertEquals("0", resp.asString());
    }

    @Test(timeout = 60000)
    public void testPatFoundAtNonZeroIndexWhenReverseAlsoPresentReturnsThatIndex() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "setup-" + uuid).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", "xxABAB BABAxx".replace(" ", ""), "ABAB");
        assertEquals("2", resp.asString());
    }
}