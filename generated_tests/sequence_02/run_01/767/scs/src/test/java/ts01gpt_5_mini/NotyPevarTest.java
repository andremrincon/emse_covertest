package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class NotyPevarTest {
    @BeforeClass
    public static void setup() {
        String base = System.getenv("API_BASE");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("api.base", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testNotyPevar_whenI28AndLargeS_returnsThree() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/text2txt/{w}/{x}/{y}", "arr"+uid, "quick", "brown").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", 28, "zzz");
        Assert.assertEquals("3", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testNotyPevar_whenI7TriggersConcatBranch_returnsThree() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/text2txt/{w}/{x}/{y}", "arr"+uid, "foo", "bar").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", 7, "a");
        Assert.assertEquals("3", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testNotyPevar_whenI5AndSmallS_returnsZero() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/text2txt/{w}/{x}/{y}", "arr"+uid, "one", "two").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", 5, "a");
        Assert.assertEquals("0", resp.getBody().asString());
    }
}