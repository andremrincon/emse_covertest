package ts01glm_5_2;

import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class Ordered4Test {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("baseUrl");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testUnorderedWhenStringLengthTooShort() {
        given().when().get("/api/ordered4/abcd/bbbbb/ccccc/ddddd").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedWhenStringLengthTooLong() {
        given().when().get("/api/ordered4/bbbbbbb/ccccc/ddddd/eeeee").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIncreasingOrder() {
        given().when().get("/api/ordered4/aaaab/bbbba/dddde/ccccd").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDecreasingOrder() {
        given().when().get("/api/ordered4/zzzzzz/yyyyzz/wwwwzz/xxxxxy").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedWhenAllStringsEqual() {
        given().when().get("/api/ordered4/mmmmm/mmmmm/mmmmm/mmmmm").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedWhenPartialOrdering() {
        given().when().get("/api/ordered4/yyyyy/xxxxx/zzzzz/yyyyz").then().statusCode(200);
    }
}