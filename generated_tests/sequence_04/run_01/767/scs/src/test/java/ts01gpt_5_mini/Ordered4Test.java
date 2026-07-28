package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class Ordered4Test {

    @BeforeClass
    public static void setup() {
        String env = System.getProperty("baseUrl");
        if (env == null || env.isEmpty()) {
            env = System.getenv("BASE_URL");
        }
        if (env == null || env.isEmpty()) {
            env = "http://localhost:8080";
        }
        RestAssured.baseURI = env;
    }

    @Test(timeout = 60000)
    public void testIncreasingOrder() {
        given().when().get("/api/pat/{txt}", "health-check").then().statusCode(lessThan(300));
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String w = "a" + uuid.substring(0,4);
        String x = "b" + uuid.substring(4,8);
        String y = "c" + uuid.substring(8,12);
        String z = "d" + uuid.substring(12,16);
        Response resp = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", w, x, z, y);
        assertEquals("increasing", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testDecreasingOrder() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "The", "quick", "brown").then().statusCode(lessThan(300));
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String w = "z" + uuid.substring(0,4);
        String x = "y" + uuid.substring(4,8);
        String y = "x" + uuid.substring(8,12);
        String z = "w" + uuid.substring(12,16);
        Response resp = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", w, x, z, y);
        assertEquals("decreasing", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testUnorderedByComparison() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "add", "15.5", "4.5").then().statusCode(lessThan(300));
        String w = "apple";
        String x = "banana";
        String z = "cherry";
        String y = "doggg";
        Response resp = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", w, x, z, y);
        assertEquals("unordered", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testUnorderedByLength() {
        given().when().get("/api/costfuns/{i}/{s}", "1", "algorithm").then().statusCode(lessThan(300));
        String w = "four";
        String x = "short";
        String z = "abcde";
        String y = "abcdf";
        Response resp = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", w, x, z, y);
        assertEquals("unordered", resp.getBody().asString());
    }
}