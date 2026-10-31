package ts01gpt_5_mini_3;

import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Text2TxtTest {

    @BeforeClass
    public static void setup() {
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
    public void testTwoBranch() {
        String id = UUID.randomUUID().toString();
        given().when().get("/api/text2txt/{w}/{x}/{y}", "and", "alpha" + id, "beta").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "for", "alpha" + id, "beta").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/{w}/{x}/{y}", "two", "one", "three");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testForBranch() {
        String id = UUID.randomUUID().toString();
        given().when().get("/api/text2txt/{w}/{x}/{y}", "two", "x" + id, "y").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "four", "q" + id, "r").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/{w}/{x}/{y}", "for", "quick", "brown");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testYouBranch() {
        String id = UUID.randomUUID().toString();
        given().when().get("/api/text2txt/{w}/{x}/{y}", "and", "a" + id, "b").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "see", "you", "now").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/{w}/{x}/{y}", "you", "hello", "world");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAreSkipsElseIf() {
        String id = UUID.randomUUID().toString();
        given().when().get("/api/text2txt/{w}/{x}/{y}", "see", "you", "later" + id).then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "by", "the", "way").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/{w}/{x}/{y}", "are", "see", "you");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSeeYouBranch() {
        String id = UUID.randomUUID().toString();
        given().when().get("/api/text2txt/{w}/{x}/{y}", "are", "something" + id, "else").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "for", "foo", "bar").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/{w}/{x}/{y}", "see", "you", "now");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testByTheWayBranch() {
        String id = UUID.randomUUID().toString();
        given().when().get("/api/text2txt/{w}/{x}/{y}", "you", "u" + id, "v").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "two", "t" + id, "w").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/{w}/{x}/{y}", "by", "the", "way");
        act.then().statusCode(200);
    }
}