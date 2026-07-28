package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class Ordered4Test {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testIncreasingOrderProducesIncreasingBody() {
        given().when().get("/api/pat/arrange-incr").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/ordered4/aaaaa/bbbbb/ddddd/ccccc");
        Assert.assertEquals("increasing", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testDecreasingOrderProducesDecreasingBody() {
        given().when().get("/api/pat/arrange-decr").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/ordered4/zzzzz/yyyyy/wwwww/xxxxx");
        Assert.assertEquals("decreasing", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testEqualStringsWithinLengthProduceUnorderedBody() {
        given().when().get("/api/pat/arrange-eq").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/ordered4/apple/apple/apple/apple");
        Assert.assertEquals("unordered", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testLengthViolationProducesUnorderedBody() {
        given().when().get("/api/pat/arrange-len").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/ordered4/tiny/bbbbb/ccccc/ddddd");
        Assert.assertEquals("unordered", act.getBody().asString());
    }
}