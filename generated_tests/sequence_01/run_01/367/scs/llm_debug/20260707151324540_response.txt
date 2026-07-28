package ts01gpt_5_mini;

import org.junit.Test;
import org.junit.BeforeClass;
import io.restassured.response.Response;
import java.util.UUID;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class Ordered4Test {

    private static String base;

    @BeforeClass
    public static void setup() {
        String prop = System.getProperty("api.base");
        String env = System.getenv("API_BASE_URL");
        base = prop != null ? prop : (env != null ? env : "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testIncreasingOrderReturnsIncreasing() {
        String uuid = UUID.randomUUID().toString();
        given().when().get(base + "/api/pat/" + uuid).then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/ordered4/{w}/{x}/{z}/{y}", "aaaaa", "bbbbb", "ddddd", "ccccc");
        resp.then().body(equalTo("increasing"));
    }

    @Test(timeout = 60000)
    public void testDecreasingOrderReturnsDecreasing() {
        String uuid = UUID.randomUUID().toString();
        given().when().get(base + "/api/pat/" + uuid).then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/ordered4/{w}/{x}/{z}/{y}", "ddddd", "ccccc", "aaaaa", "bbbbb");
        resp.then().body(equalTo("decreasing"));
    }

    @Test(timeout = 60000)
    public void testEqualValuesWithinLengthReturnUnordered() {
        String uuid = UUID.randomUUID().toString();
        given().when().get(base + "/api/pat/" + uuid).then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/ordered4/{w}/{x}/{z}/{y}", "apple", "apple", "apple", "apple");
        resp.then().body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testLengthOutOfRangeReturnsUnordered() {
        String uuid = UUID.randomUUID().toString();
        given().when().get(base + "/api/pat/" + uuid).then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/ordered4/{w}/{x}/{z}/{y}", "four", "bbbbb", "ddddd", "ccccc");
        resp.then().body(equalTo("unordered"));
    }
}