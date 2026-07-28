package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class Ordered4Test {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
            if (base == null || base.isEmpty()) {
                base = "http://localhost:8080";
            }
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testIncreasingOrderReturns200() {
        String marker = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", marker).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "aaaaa", "bbbbb", "ddddd", "ccccc");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDecreasingOrderReturns200() {
        String marker = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", marker).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "ddddd", "ccccc", "aaaaa", "bbbbb");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEqualStringsProduceUnorderedReturns200() {
        String marker = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", marker).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "apple", "apple", "apple", "apple");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testLengthOutOfRangeProducesUnorderedReturns200() {
        String marker = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", marker).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "four", "bbbbb", "ddddd", "ccccc");
        resp.then().statusCode(200);
    }
}