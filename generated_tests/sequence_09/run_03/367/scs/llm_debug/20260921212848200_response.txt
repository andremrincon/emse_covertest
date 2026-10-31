package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class CookieTest {
    private static String BASE;

    @BeforeClass
    public static void init() {
        String prop = System.getProperty("base.url");
        String env = System.getenv("BASE_URL");
        if (prop != null && !prop.isEmpty()) {
            BASE = prop;
        } else if (env != null && !env.isEmpty()) {
            BASE = env;
        } else {
            BASE = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testUseridStartsWithUserProducesOne() {
        String id = UUID.randomUUID().toString();
        given().when().get(BASE + "/api/pat/a").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/cookie/{name}/{val}/{site}", "userid", "user12345-" + id, "example.com").then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testUseridLongButNotStartingWithUserProducesZero() {
        given().when().get(BASE + "/api/pat/a").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/cookie/{name}/{val}/{site}", "userid", "abcdefg12345", "localhost").then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testSessionAmAndAbcComProducesOne() {
        given().when().get(BASE + "/api/pat/a").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/cookie/{name}/{val}/{site}", "session", "am", "abc.com").then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testSessionOtherValuesProduceTwo() {
        String unique = UUID.randomUUID().toString();
        given().when().get(BASE + "/api/pat/a").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/cookie/{name}/{val}/{site}", "session", "am-" + unique, "example.com").then().body(equalTo("2"));
    }
}