package ts01gpt_5_mini_2;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import java.util.UUID;
import static org.hamcrest.Matchers.lessThan;

public class Text2TxtTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null) base = System.getenv("BASE_URL");
        if (base == null) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testSeeYouBranchReturns200() {
        String uuid = UUID.randomUUID().toString();
        RestAssured.given().when().get("/api/pat/{txt}", "warmup-" + uuid).then().statusCode(lessThan(300));
        RestAssured.given().when().get("/api/text2txt/{w}/{x}/{y}", "see", "you", uuid).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testByTheWayBranchReturns200() {
        RestAssured.given().when().get("/api/pat/{txt}", "init-bytheway").then().statusCode(lessThan(300));
        RestAssured.given().when().get("/api/text2txt/{w}/{x}/{y}", "by", "the", "way").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAreBranchReturns200() {
        String uniq = UUID.randomUUID().toString();
        RestAssured.given().when().get("/api/pat/{txt}", "setup-" + uniq).then().statusCode(lessThan(300));
        RestAssured.given().when().get("/api/text2txt/{w}/{x}/{y}", "are", "you", "ready-" + uniq).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTwoBranchReturns200() {
        RestAssured.given().when().get("/api/pat/{txt}", "setup-two").then().statusCode(lessThan(300));
        RestAssured.given().when().get("/api/text2txt/{w}/{x}/{y}", "two", "birds", "one").then().statusCode(200);
    }
}