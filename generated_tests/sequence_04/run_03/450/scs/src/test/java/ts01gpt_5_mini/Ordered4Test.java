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
        String base = System.getProperty("api.base", System.getenv().getOrDefault("API_BASE", "http://localhost:8080"));
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testIncreasingPathReturnsIncreasing() {
        String arrangeId1 = UUID.randomUUID().toString();
        String arrangeId2 = UUID.randomUUID().toString();
        String arrangeId3 = UUID.randomUUID().toString();
        given().when().get("/api/text2txt/{w}/{x}/{y}", arrangeId1, arrangeId2, arrangeId3).then().statusCode(lessThan(300));
        String w = "aaaaa";
        String x = "bbbbb";
        String y = "ccccc";
        String z = "ddddd";
        Response resp = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", w, x, z, y);
        assertEquals("increasing", resp.asString());
    }

    @Test(timeout = 60000)
    public void testDecreasingPathReturnsDecreasing() {
        String arrangeId1 = UUID.randomUUID().toString();
        String arrangeId2 = UUID.randomUUID().toString();
        String arrangeId3 = UUID.randomUUID().toString();
        given().when().get("/api/text2txt/{w}/{x}/{y}", arrangeId1, arrangeId2, arrangeId3).then().statusCode(lessThan(300));
        String w = "zzzzz";
        String x = "yyyyy";
        String y = "xxxxx";
        String z = "wwwww";
        Response resp = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", w, x, z, y);
        assertEquals("decreasing", resp.asString());
    }

    @Test(timeout = 60000)
    public void testLengthsOutOfRangeReturnUnordered() {
        String arrangeId1 = UUID.randomUUID().toString();
        String arrangeId2 = UUID.randomUUID().toString();
        String arrangeId3 = UUID.randomUUID().toString();
        given().when().get("/api/text2txt/{w}/{x}/{y}", arrangeId1, arrangeId2, arrangeId3).then().statusCode(lessThan(300));
        String w = "abc";
        String x = "bbbbb";
        String y = "ccccc";
        String z = "ddddd";
        Response resp = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", w, x, z, y);
        assertEquals("unordered", resp.asString());
    }

    @Test(timeout = 60000)
    public void testEqualStringsReturnUnordered() {
        String arrangeId1 = UUID.randomUUID().toString();
        String arrangeId2 = UUID.randomUUID().toString();
        String arrangeId3 = UUID.randomUUID().toString();
        given().when().get("/api/text2txt/{w}/{x}/{y}", arrangeId1, arrangeId2, arrangeId3).then().statusCode(lessThan(300));
        String w = "apple";
        String x = "apple";
        String y = "apple";
        String z = "apple";
        Response resp = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", w, x, z, y);
        assertEquals("unordered", resp.asString());
    }
}