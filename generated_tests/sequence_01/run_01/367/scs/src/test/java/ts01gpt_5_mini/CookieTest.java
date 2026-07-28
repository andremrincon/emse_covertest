package ts01gpt_5_mini;

import org.junit.Test;
import io.restassured.response.Response;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class CookieTest {

    private static final String BASE;
    static {
        String env = System.getenv("API_BASE_URL");
        BASE = env != null ? env : System.getProperty("api.base", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testUseridStartsWithUserReturnsOne() {
        given().when().get(BASE + "/api/calc/add/1/1").then().statusCode(lessThan(300));
        String unique = "user" + UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        Response act = given().when().get(BASE + "/api/cookie/{name}/{val}/{site}", "userid", unique, "localhost");
        act.then().assertThat().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testSessionExactMatchReturnsOne() {
        given().when().get(BASE + "/api/calc/add/1/1").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/cookie/{name}/{val}/{site}", "session", "am", "abc.com");
        act.then().assertThat().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testSessionNonMatchReturnsTwo() {
        given().when().get(BASE + "/api/calc/add/1/1").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/cookie/{name}/{val}/{site}", "session", "nope", "example.com");
        act.then().assertThat().body(equalTo("2"));
    }
}