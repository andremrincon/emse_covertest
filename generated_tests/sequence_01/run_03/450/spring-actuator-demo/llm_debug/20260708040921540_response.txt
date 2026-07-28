package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class SampleControllerTest {

    @BeforeClass
    public static void setup() {
        String url = System.getenv("BASE_URL");
        if (url == null || url.isEmpty()) {
            url = System.getProperty("baseUrl");
        }
        if (url == null || url.isEmpty()) {
            url = "http://localhost:8080";
        }
        RestAssured.baseURI = url;
    }

    @Test(timeout = 60000)
    public void testSayHelloWithName() {
        given().when().get("/").then().statusCode(lessThan(300));
        given().param("name", "John Smith").when().get("/").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSayHelloDefaultName() {
        given().when().get("/").then().statusCode(lessThan(300));
        given().when().get("/").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSlowApiWithDelayZeroTriggersRandomBranch() {
        given().when().get("/").then().statusCode(lessThan(300));
        given().param("delay", "0").when().get("/slowApi").then().statusCode(401);
    }

    @Test(timeout = 60000)
    public void testSlowApiWithExplicitDelayReturns200() {
        given().when().get("/").then().statusCode(lessThan(300));
        given().param("delay", "1").when().get("/slowApi").then().statusCode(401);
    }
}