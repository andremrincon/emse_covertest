package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class Text2TxtTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testWord1TwoProducesOk() {
        given().when().get("/api/text2txt/{w1}/{w2}/{w3}", "The", "quick", "brown").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/{w1}/{w2}/{w3}", "two", "is", "here");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testWord1ForProducesOk() {
        given().when().get("/api/text2txt/{w1}/{w2}/{w3}", "a", "b", "c").then().statusCode(lessThan(300));
        String uid = UUID.randomUUID().toString();
        Response resp = given().when().get("/api/text2txt/{w1}/{w2}/{w3}", "for", uid, "x");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSeeYouSequenceProducesOk() {
        given().when().get("/api/text2txt/{w1}/{w2}/{w3}", "The", "quick", "brown").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/{w1}/{w2}/{w3}", "see", "you", "now");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testByTheWaySequenceProducesOk() {
        given().when().get("/api/text2txt/{w1}/{w2}/{w3}", "hello", "world", "x").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/{w1}/{w2}/{w3}", "by", "the", "way");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testWordAreBranchProducesOk() {
        given().when().get("/api/text2txt/{w1}/{w2}/{w3}", "sample", "text", "here").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/{w1}/{w2}/{w3}", "are", "you", "okay");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnknownWordsProduceOk() {
        given().when().get("/api/text2txt/{w1}/{w2}/{w3}", "The", "quick", "brown").then().statusCode(lessThan(300));
        String unique = "u" + UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        Response resp = given().when().get("/api/text2txt/{w1}/{w2}/{w3}", unique, "alpha", "beta");
        resp.then().statusCode(200);
    }
}