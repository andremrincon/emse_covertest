package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class Text2TxtTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) {
            String env = System.getenv("BASE_URL");
            base = (env == null || env.isEmpty()) ? "http://localhost:8080" : env;
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testTwoReturns200() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "The", "quick", "brown").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "see", "you", "later").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/{w}/{x}/{y}", "two", "anything", "else");
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testForReturns200() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "two", "a", "b").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "four", "x", "y").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/{w}/{x}/{y}", "for", "some", "thing");
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testYouReturns200() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "for", "x", "y").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "and", "x", "y").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/{w}/{x}/{y}", "you", "will", "see");
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testAndReturns200() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "you", "x", "y").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "are", "x", "y").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/{w}/{x}/{y}", "and", "x", "y");
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testAreAndSeeYouBranchingReturns200() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "and", "x", "y").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "see", "you", "now").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/{w}/{x}/{y}", "are", "we", "ok");
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testByTheWayReturns200() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "are", "x", "y").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "see", "you", "later").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/{w}/{x}/{y}", "by", "the", "way");
        Assert.assertEquals(200, resp.getStatusCode());
    }
}