package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.net.URLEncoder;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;

public class PatTest {

    @BeforeClass
    public static void setup() {
        String env = System.getenv("API_BASE_URL");
        String prop = System.getProperty("api.base");
        String base = env != null && !env.isEmpty() ? env : (prop != null && !prop.isEmpty() ? prop : "http://localhost:8080");
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testPat_ShortPattern_Returns200() throws Exception {
        String txt = URLEncoder.encode("The quick brown fox", "UTF-8");
        given().when().get("/api/pat/{txt}", txt).then().statusCode(lessThan(300));
        String pat = URLEncoder.encode("ab", "UTF-8");
        Response res = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        res.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPat_PatternFound_Returns200() throws Exception {
        String txtRaw = "xxABCyy";
        String patRaw = "ABC";
        String txt = URLEncoder.encode(txtRaw, "UTF-8");
        given().when().get("/api/pat/{txt}", txt).then().statusCode(lessThan(300));
        String pat = URLEncoder.encode(patRaw, "UTF-8");
        Response res = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        res.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPat_ReverseFoundOnly_Returns200() throws Exception {
        String patRaw = "ABC";
        String patrevRaw = new StringBuilder(patRaw).reverse().toString();
        String txtRaw = "start" + patrevRaw + "end";
        String txt = URLEncoder.encode(txtRaw, "UTF-8");
        given().when().get("/api/pat/{txt}", txt).then().statusCode(lessThan(300));
        String pat = URLEncoder.encode(patRaw, "UTF-8");
        Response res = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        res.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPat_PalindromeAdjacent_Returns200() throws Exception {
        String patRaw = "ABC";
        String txtRaw = "XX" + patRaw + new StringBuilder(patRaw).reverse().toString() + "YY";
        String txt = URLEncoder.encode(txtRaw, "UTF-8");
        given().when().get("/api/pat/{txt}", txt).then().statusCode(lessThan(300));
        String pat = URLEncoder.encode(patRaw, "UTF-8");
        Response res = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        res.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPat_TextOnlyEndpoint_Returns200() throws Exception {
        String txtRaw = "The quick brown fox jumps over the lazy dog.";
        String txt = URLEncoder.encode(txtRaw, "UTF-8");
        given().when().get("/api/pat/{txt}", txt).then().statusCode(lessThan(300));
        Response res = given().when().get("/api/pat/{txt}", txt);
        res.then().statusCode(200);
    }
}