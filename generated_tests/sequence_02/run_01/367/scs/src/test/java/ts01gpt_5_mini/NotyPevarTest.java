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
    public static void init() {
        String base = System.getProperty("base.url");
        if (base == null) base = System.getenv("BASE_URL");
        if (base == null) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testSubjectReturns28WhenSumEquals56() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "Setup "+uid).then().statusCode(lessThan(300));
        given().when().get("/api/notypevar/{i}/{s}", 7, "arrange").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", 28, "any").then().statusCode(200).extract().response();
        assertEquals("3", resp.asString().trim());
    }

    @Test(timeout = 60000)
    public void testSubjectReturns2WhenCompareToLessThanZero() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "add", "15.5", "4.5").then().statusCode(lessThan(300));
        given().when().get("/api/notypevar/{i}/{s}", 6, "arrange").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", 5, "zulu").then().statusCode(200).extract().response();
        assertEquals("2", resp.asString().trim());
    }

    @Test(timeout = 60000)
    public void testSubjectReturns1WhenXsPlusYEqualsHello7() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/text2txt/{word1}/{word2}/{word3}", "The", "quick", uid).then().statusCode(lessThan(300));
        given().when().get("/api/notypevar/{i}/{s}", 28, "setup").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", 7, "ignored").then().statusCode(200).extract().response();
        assertEquals("3", resp.asString().trim());
    }
}