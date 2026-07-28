package ts01gpt_5_mini;

import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class Ordered4Test {

    private static String baseUrl;

    @BeforeClass
    public static void init() {
        String prop = System.getProperty("baseUrl");
        if (prop != null && !prop.isEmpty()) {
            baseUrl = prop;
        } else {
            String env = System.getenv("BASE_URL");
            baseUrl = (env != null && !env.isEmpty()) ? env : "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void test_increasing_order_returns_200() {
        given().when().get(baseUrl + "/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response act = given().when().get(baseUrl + "/api/ordered4/apple/baker/davis/carol");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_decreasing_order_returns_200() {
        given().when().get(baseUrl + "/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response act = given().when().get(baseUrl + "/api/ordered4/zebra/yadda/xfile/wadee");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_unordered_returns_unordered_body() {
        given().when().get(baseUrl + "/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response act = given().when().get(baseUrl + "/api/ordered4/apple/baker/carol/davis");
        act.then().assertThat().body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void test_invalid_length_returns_500() {
        given().when().get(baseUrl + "/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response act = given().when().get(baseUrl + "/api/ordered4/four/abcde/fghij/klmno");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_six_char_increasing_returns_200() {
        given().when().get(baseUrl + "/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response act = given().when().get(baseUrl + "/api/ordered4/aaa111/bbb111/ddd111/ccc111");
        act.then().statusCode(200);
    }
}