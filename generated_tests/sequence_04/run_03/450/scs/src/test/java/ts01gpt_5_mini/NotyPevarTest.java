package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class NotyPevarTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testCompareToBranch() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        given().when().get("/api/notypevar/{i}/{s}", 0, "world").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testHelloConcatBranch() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        given().when().get("/api/notypevar/{i}/{s}", 7, "a").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSumEquals56Branch() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        given().when().get("/api/notypevar/{i}/{s}", 28, "alpha").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testYGreaterThanXBranch() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        given().when().get("/api/notypevar/{i}/{s}", 6, "a").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testInvalidIntegerParameterReturns400() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        given().when().get("/api/notypevar/{i}/{s}", "abc", "a").then().statusCode(400);
    }
}