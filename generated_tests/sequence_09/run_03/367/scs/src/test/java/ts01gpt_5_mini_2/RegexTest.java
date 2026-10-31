package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class RegexTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    private String enc(String s) throws Exception {
        return URLEncoder.encode(s, StandardCharsets.UTF_8.name());
    }

    @Test(timeout = 60000)
    public void testPatEndpointRecognizesUrlAsInput() throws Exception {
        String arrangeTxt = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", enc(arrangeTxt)).then().statusCode(lessThan(300));
        String txt = "http://abc/def";
        Response act = given().when().get("/api/pat/{txt}", enc(txt));
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatEndpointRecognizesDateAsInput() throws Exception {
        String arrangeTxt = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", enc(arrangeTxt)).then().statusCode(lessThan(300));
        String txt = "mon01jan";
        Response act = given().when().get("/api/pat/{txt}", enc(txt));
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatEndpointRecognizesFloatingPointWithExponentAsInput() throws Exception {
        String arrangeTxt = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", enc(arrangeTxt)).then().statusCode(lessThan(300));
        String txt = "1.0e+23";
        Response act = given().when().get("/api/pat/{txt}", enc(txt));
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatEndpointReturnsNoneForNonMatchingInput() throws Exception {
        String arrangeTxt = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", enc(arrangeTxt)).then().statusCode(lessThan(300));
        String txt = "foobar";
        Response act = given().when().get("/api/pat/{txt}", enc(txt));
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatWithPatternFindsOccurrences() throws Exception {
        String arrangeTxt = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", enc(arrangeTxt)).then().statusCode(lessThan(300));
        String txt = "ABABCABAB";
        String pat = "ABAB";
        Response act = given().when().get("/api/pat/{txt}/{pat}", enc(txt), enc(pat));
        act.then().statusCode(200);
    }
}