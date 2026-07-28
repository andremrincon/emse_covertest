package ts01gpt_5_mini;

import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CookieTest {
    private final String base;
    public CookieTest() {
        String b = System.getProperty("api.base");
        if (b == null) b = System.getenv("API_BASE_URL");
        if (b == null) b = "http://localhost:8080";
        this.base = b;
    }

    @Test(timeout = 60000)
    public void testUseridReturnsOneWhenValStartsWithUserAndLongEnough() {
        given().when().get(base + "/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        String val = "user" + UUID.randomUUID().toString().replace("-", "");
        given().when().get(base + "/api/cookie/userid/" + val + "/example.com").then().assertThat().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testSessionReturnsOneForAmAndAbcCom() {
        given().when().get(base + "/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        given().when().get(base + "/api/cookie/session/am/abc.com").then().assertThat().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testSessionReturnsTwoForNonMatchingValues() {
        given().when().get(base + "/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        given().when().get(base + "/api/cookie/session/notam/other.com").then().assertThat().body(equalTo("2"));
    }
}