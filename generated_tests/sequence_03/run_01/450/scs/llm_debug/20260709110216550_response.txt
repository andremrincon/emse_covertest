package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Ordered4Test {

    @BeforeClass
    public static void setup() {
        String env = System.getProperty("api.base");
        if (env == null || env.isEmpty()) {
            String e2 = System.getenv("API_BASE");
            if (e2 == null || e2.isEmpty()) {
                RestAssured.baseURI = "http://localhost:8080";
            } else {
                RestAssured.baseURI = e2;
            }
        } else {
            RestAssured.baseURI = env;
        }
    }

    @Test(timeout = 60000)
    public void testIncreasingOrderReturnsIncreasing() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uid).then().statusCode(lessThan(300));
        given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "aaaaa", "bbbbb", "ddddd", "ccccc")
               .then().body(equalTo("increasing"));
    }

    @Test(timeout = 60000)
    public void testDecreasingOrderReturnsDecreasing() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uid).then().statusCode(lessThan(300));
        given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "zzzzz", "yyyyy", "wwwww", "xxxxx")
               .then().body(equalTo("decreasing"));
    }

    @Test(timeout = 60000)
    public void testLengthsOutOfRangeReturnUnordered() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uid).then().statusCode(lessThan(300));
        given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "four", "bbbbb", "ddddd", "ccccc")
               .then().body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testEqualStringsReturnUnordered() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uid).then().statusCode(lessThan(300));
        given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "apple", "apple", "apple", "apple")
               .then().body(equalTo("unordered"));
    }
}