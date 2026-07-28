package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class Ordered4Test {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null) base = System.getenv("API_BASE");
        if (base == null) base = System.getenv("API_BASE_URL");
        if (base == null) base = System.getenv("BASE_URL");
        if (base == null) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testIncreasingOrderProducesIncreasing() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/text2txt/{a}/{b}/{c}", uid, uid, uid).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "aaaaa", "aaaab", "aaaad", "aaaac");
        resp.then().body(equalTo("increasing"));
    }

    @Test(timeout = 60000)
    public void testDecreasingOrderProducesDecreasing() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/text2txt/{a}/{b}/{c}", uid, uid, uid).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "zzzzz", "yyyyy", "wwwww", "xxxxx");
        resp.then().body(equalTo("decreasing"));
    }

    @Test(timeout = 60000)
    public void testEqualStringsWithinRangeProduceUnordered() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/text2txt/{a}/{b}/{c}", uid, uid, uid).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "abcde", "abcde", "abcde", "abcde");
        resp.then().body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testLengthOutOfRangeProducesUnordered() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/text2txt/{a}/{b}/{c}", uid, uid, uid).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "abcdefg", "aaaaa", "aaaaa", "aaaaa");
        resp.then().body(equalTo("unordered"));
    }
}