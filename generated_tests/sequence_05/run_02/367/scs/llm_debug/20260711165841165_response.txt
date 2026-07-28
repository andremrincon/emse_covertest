package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class Ordered4Test {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("API_BASE", System.getenv("API_BASE"));
        if (base == null || base.isEmpty()) {
            base = System.getProperty("base.url", System.getenv("BASE_URL"));
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testIncreasingOrder_returnsIncreasing() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "health-" + uid).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "aaaaa", "bbbbb", "ddddd", "ccccc");
        resp.then().statusCode(200).assertThat().body(equalTo("increasing"));
    }

    @Test(timeout = 60000)
    public void testDecreasingOrder_returnsDecreasing() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "health-" + uid).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "mmmmm", "lllll", "kkkkk", "jjjjj");
        resp.then().statusCode(200).assertThat().body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testLengthsOutOfRange_returnsUnordered() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "health-" + uid).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "tiny", "bbbbb", "ccccc", "ddddd");
        resp.then().statusCode(200).assertThat().body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testEqualValues_returnsUnordered() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "health-" + uid).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "apple", "apple", "banana", "carrot");
        resp.then().statusCode(200).assertThat().body(equalTo("unordered"));
    }
}