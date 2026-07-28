package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

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
    public void testIncreasingReturnsIncreasingBody() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uid).then().statusCode(lessThan(300));
        given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "aaaaa", "bbbbb", "ddddd", "ccccc").then().assertThat().body(equalTo("increasing"));
    }

    @Test(timeout = 60000)
    public void testDecreasingReturnsDecreasingBody() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/text2txt/{word1}/{word2}/{word3}", uid, "x", "y").then().statusCode(lessThan(300));
        given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "zzzzz", "yyyyy", "wwwww", "xxxxx").then().assertThat().body(equalTo("decreasing"));
    }

    @Test(timeout = 60000)
    public void testUnorderedDueToLengthReturnsUnorderedBody() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "add", "1", "2").then().statusCode(lessThan(300));
        given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "four", "bbbbb", "ccccc", "ddddd").then().assertThat().body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testUnorderedDueToOrderMismatchReturnsUnorderedBody() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/cookie/{name}/{val}/{site}", uid, "val", "example.com").then().statusCode(lessThan(300));
        given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "aaaaa", "ccccc", "ddddd", "bbbbb").then().assertThat().body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testOrdered4EndpointReturns200Status() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/notypevar/{i}/{s}", "5", uid).then().statusCode(lessThan(300));
        given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "abcde", "fghij", "pqrst", "klmno").then().statusCode(200);
    }
}