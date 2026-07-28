package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class NotyPevarTest {

    @BeforeClass
    public static void setUp() {
        String base = System.getProperty("API_BASE");
        if (base == null) base = System.getenv("API_BASE");
        if (base == null) base = System.getProperty("api.base");
        if (base == null) base = System.getenv("api.base");
        if (base == null) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testNoneCondition_returnsZero() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/notypevar/5/hello");
        assertEquals("0", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testCompareToBranch_returnsTwo() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        String unique = UUID.randomUUID().toString();
        Response act = given().when().get("/api/notypevar/0/world");
        assertEquals("2", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testHello7Branch_status200() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/notypevar/7/aaa");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSum56Branch_status200() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/notypevar/28/hello");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGreaterThanFiveBranch_status200() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/notypevar/6/hello");
        act.then().statusCode(200);
    }
}