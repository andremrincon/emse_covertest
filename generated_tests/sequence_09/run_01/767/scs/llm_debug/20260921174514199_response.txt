package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class RegexTest {

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
    public void testSubject_urlBranch() {
        given().when().get("/api/pat/{txt}", UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        Response act = given().pathParam("txt", "http://a/b").when().get("/api/pat/{txt}");
        act.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testSubject_dateBranch() {
        given().when().get("/api/pat/{txt}", UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        Response act = given().pathParam("txt", "wed12jan").when().get("/api/pat/{txt}");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubject_fpeBranch() {
        given().when().get("/api/pat/{txt}", UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        Response act = given().pathParam("txt", "12.34e+56").when().get("/api/pat/{txt}");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubject_noneBranch() {
        given().when().get("/api/pat/{txt}", UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        Response act = given().pathParam("txt", "random_text_zzz").when().get("/api/pat/{txt}");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPat_withPattern_returns200() {
        given().when().get("/api/pat/{txt}", UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        String txt = "ABABCABAB";
        String pat = "ABAB";
        Response act = given().pathParam("txt", txt).pathParam("pat", pat).when().get("/api/pat/{txt}/{pat}");
        act.then().statusCode(200);
    }
}