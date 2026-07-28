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
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testIncreasingOrderProducesIncreasing() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/text2txt/{a}/{b}/{c}", "The", "quick", uid).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "aaaaa", "bbbbb", "ddddd", "ccccc");
        assertEquals("increasing", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testDecreasingOrderProducesDecreasing() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/text2txt/{a}/{b}/{c}", "a", "b", uid).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "zzzzz", "yyyyy", "wwwww", "xxxxx");
        assertEquals("decreasing", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testEqualStringsProduceUnordered() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/text2txt/{a}/{b}/{c}", "one", "two", uid).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "abcde", "abcde", "abcde", "abcde");
        assertEquals("unordered", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testLengthOutOfRangeProducesUnordered() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/text2txt/{a}/{b}/{c}", "x", "y", uid).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "four", "bbbbb", "ddddd", "ccccc");
        assertEquals("unordered", act.getBody().asString());
    }
}