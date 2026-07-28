package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class Ordered4Test {

    private static String base;

    @BeforeClass
    public static void init() {
        String env = System.getProperty("api.base");
        if (env != null && !env.isEmpty()) {
            base = env;
        } else {
            String e2 = System.getenv("API_BASE");
            base = (e2 != null && !e2.isEmpty()) ? e2 : "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void test_increasing_order_returns_increasing() {
        given().when().get(base + "/api/pat/TheQuickBrownFox").then().statusCode(lessThan(300));
        Response response = given().when().get(base + "/api/ordered4/apple/berry/datee/cherry");
        response.then().body(equalTo("increasing"));
    }

    @Test(timeout = 60000)
    public void test_decreasing_order_returns_decreasing() {
        given().when().get(base + "/api/pat/SampleTextForSetup").then().statusCode(lessThan(300));
        Response response = given().when().get(base + "/api/ordered4/zzzzz/yyyyy/wwwww/xxxxx");
        response.then().body(equalTo("decreasing"));
    }

    @Test(timeout = 60000)
    public void test_unordered_when_lengths_ok_but_not_ordered() {
        given().when().get(base + "/api/pat/SetupText").then().statusCode(lessThan(300));
        Response response = given().when().get(base + "/api/ordered4/apple/cherry/datee/berry");
        response.then().body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void test_unordered_when_length_out_of_range() {
        given().when().get(base + "/api/pat/Arrange").then().statusCode(lessThan(300));
        Response response = given().when().get(base + "/api/ordered4/four/berry/datee/cherry");
        response.then().body(equalTo("unordered"));
    }
}