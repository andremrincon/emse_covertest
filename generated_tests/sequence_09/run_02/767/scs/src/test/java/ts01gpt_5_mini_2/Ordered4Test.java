package ts01gpt_5_mini_2;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.given;

public class Ordered4Test {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testIncreasingOrderProducesIncreasing() {
        Response r1 = given().when().get("/api/calc/add/15.5/4.5");
        r1.then().statusCode(lessThan(300));
        Response r2 = given().when().get("/api/ordered4/aaaaa/bbbbb/ddddd/ccccc");
        r2.then().body(equalTo("increasing"));
    }

    @Test(timeout = 60000)
    public void testDecreasingOrderProducesDecreasing() {
        Response r1 = given().when().get("/api/calc/subtract/15.5/4.5");
        r1.then().statusCode(lessThan(300));
        Response r2 = given().when().get("/api/ordered4/zzzzz/yyyyy/wwwww/xxxxx");
        r2.then().body(equalTo("decreasing"));
    }

    @Test(timeout = 60000)
    public void testValidLengthsButUnorderedProducesUnordered() {
        Response r1 = given().when().get("/api/cookie/session-id/abc-123-xyz-789/example.com");
        r1.then().statusCode(lessThan(300));
        Response r2 = given().when().get("/api/ordered4/apple/mango/grape/peach");
        r2.then().body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testLengthOutOfRangeProducesUnordered() {
        Response r1 = given().when().get("/api/text2txt/The/quick/brown");
        r1.then().statusCode(lessThan(300));
        Response r2 = given().when().get("/api/ordered4/four/bbbbb/ccccc/ddddd");
        r2.then().body(equalTo("unordered"));
    }
}