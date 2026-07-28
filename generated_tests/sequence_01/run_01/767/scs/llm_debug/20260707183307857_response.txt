package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class RegexTest {

    @BeforeClass
    public static void setup() {
        String prop = System.getProperty("baseUrl");
        String env = System.getenv("BASE_URL");
        String base = (prop != null && !prop.isEmpty()) ? prop : (env != null && !env.isEmpty() ? env : "http://localhost:8080");
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void test_pat_url_matches_subject_returns_200() {
        given().when().get("/api/pat/{txt}", UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        String raw = "http://abc/def";
        String encoded = raw.replace("/", "%2F");
        Response act = given().when().get("/api/pat/{txt}", encoded);
        assertEquals(200, act.statusCode());
    }

    @Test(timeout = 60000)
    public void test_pat_date_matches_subject_returns_200() {
        given().when().get("/api/pat/{txt}", UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        String txt = "mon12jan";
        Response act = given().when().get("/api/pat/{txt}", txt);
        assertEquals(200, act.statusCode());
    }

    @Test(timeout = 60000)
    public void test_pat_fpe_matches_subject_returns_200() {
        given().when().get("/api/pat/{txt}", UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        String txt = "1.0e+10";
        Response act = given().when().get("/api/pat/{txt}", txt);
        assertEquals(200, act.statusCode());
    }

    @Test(timeout = 60000)
    public void test_pat_none_matches_subject_returns_200() {
        given().when().get("/api/pat/{txt}", UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        String txt = "xyz";
        Response act = given().when().get("/api/pat/{txt}", txt);
        assertEquals(200, act.statusCode());
    }
}