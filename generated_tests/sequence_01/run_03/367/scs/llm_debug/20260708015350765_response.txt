package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class NotyPevarTest {
    private static String base;

    @BeforeClass
    public static void setup() {
        String env = System.getenv("BASE_URL");
        if (env != null && !env.isEmpty()) {
            base = env;
        } else {
            base = System.getProperty("baseUrl", "http://localhost:8080");
        }
    }

    @Test(timeout = 60000)
    public void testNotyPevar_returns3_for_i28_s_a() {
        given().when().get(base + "/api/pat/a").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/notypevar/28/a");
        resp.then().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testNotyPevar_returns2_for_i5_s_zzz() {
        given().when().get(base + "/api/pat/a").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/notypevar/5/zzz");
        resp.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testNotyPevar_returns0_for_i0_s_a() {
        given().when().get(base + "/api/pat/a").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/notypevar/0/a");
        resp.then().body(equalTo("0"));
    }
}