package ts01gpt_5_mini;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class NotyPevarTest {
    private static final String BASE_URL;
    static {
        String env = System.getenv("TEST_BASE_URL");
        if (env != null && !env.isEmpty()) {
            BASE_URL = env;
        } else {
            String prop = System.getProperty("TEST_BASE_URL");
            BASE_URL = (prop != null && !prop.isEmpty()) ? prop : "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void test_notypevar_returns_3_when_i_greater_than_5() {
        given().when().get(BASE_URL + "/api/pat/health").then().statusCode(lessThan(300));
        given().when().get(BASE_URL + "/api/notypevar/7/any").then().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void test_notypevar_returns_2_when_hello_compare_less_and_i_le_5() {
        given().when().get(BASE_URL + "/api/pat/health").then().statusCode(lessThan(300));
        given().when().get(BASE_URL + "/api/notypevar/0/z").then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void test_notypevar_returns_0_when_no_conditions_met_and_i_eq_5() {
        given().when().get(BASE_URL + "/api/pat/health").then().statusCode(lessThan(300));
        given().when().get(BASE_URL + "/api/notypevar/5/a").then().body(equalTo("0"));
    }
}