package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class PatTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("API_BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("api.base", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testShortPatternPathReturnsOk() {
        String txt = "TheQuickBrownFox";
        String pat = "ab";
        given().pathParam("txt", txt).when().get("/api/pat/{txt}").then().statusCode(lessThan(300));
        Response resp = given().pathParam("txt", txt).pathParam("pat", pat).when().get("/api/pat/{txt}/{pat}");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatternFoundOnlyReturnsOk() {
        String txt = "ABABCABAB";
        String pat = "ABAB";
        given().pathParam("txt", "sanityCheck").when().get("/api/pat/{txt}").then().statusCode(lessThan(300));
        Response resp = given().pathParam("txt", txt).pathParam("pat", pat).when().get("/api/pat/{txt}/{pat}");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseFoundOnlyReturnsOk() {
        String txt = "XXBABAXX";
        String pat = "ABAB";
        given().pathParam("txt", "health").when().get("/api/pat/{txt}").then().statusCode(lessThan(300));
        Response resp = given().pathParam("txt", txt).pathParam("pat", pat).when().get("/api/pat/{txt}/{pat}");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFollowedByReverseAdjacentReturnsOk() {
        String txt = "XXABCCBAXX";
        String pat = "ABC";
        given().pathParam("txt", "arrangeCheck").when().get("/api/pat/{txt}").then().statusCode(lessThan(300));
        Response resp = given().pathParam("txt", txt).pathParam("pat", pat).when().get("/api/pat/{txt}/{pat}");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseFollowedByPatAdjacentReturnsOk() {
        String txt = "XXCBAABCXX";
        String pat = "ABC";
        given().pathParam("txt", "arrange2").when().get("/api/pat/{txt}").then().statusCode(lessThan(300));
        Response resp = given().pathParam("txt", txt).pathParam("pat", pat).when().get("/api/pat/{txt}/{pat}");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSingleTxtEndpointReturnsOk() {
        String txt = "The quick brown fox jumps over the lazy dog.";
        given().pathParam("txt", txt).when().get("/api/pat/{txt}").then().statusCode(lessThan(300));
        Response resp = given().pathParam("txt", txt).when().get("/api/pat/{txt}");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoMatchPatternLongTextReturnsOk() {
        String unique = UUID.randomUUID().toString();
        String txt = "start-" + unique + "-end";
        String pat = "LONGPATTERNXYZ";
        given().pathParam("txt", "probe").when().get("/api/pat/{txt}").then().statusCode(lessThan(300));
        Response resp = given().pathParam("txt", txt).pathParam("pat", pat).when().get("/api/pat/{txt}/{pat}");
        resp.then().statusCode(200);
    }
}