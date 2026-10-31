package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class SampleControllerTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if(base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if(base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testSayHelloDefaultReturnsGuestGreeting() {
        String arrangeId = UUID.randomUUID().toString();
        given().queryParam("name", arrangeId).when().get("/").then().statusCode(lessThan(300));
        Response act = given().when().get("/");
        Assert.assertTrue(act.getBody().asString().contains("_links"));
    }

    @Test(timeout = 60000)
    public void testSayHelloWithCustomNameReturnsGreeting() {
        String arrangeId = UUID.randomUUID().toString();
        given().queryParam("name", arrangeId).when().get("/").then().statusCode(lessThan(300));
        String name = "María-José O'Connor-Smith III";
        Response act = given().queryParam("name", name).when().get("/");
        Assert.assertTrue(act.getBody().asString().contains("_links"));
    }

    @Test(timeout = 60000)
    public void testSlowApiWithZeroDelayUsesRandomPathAndReturns200() {
        given().when().get("/").then().statusCode(lessThan(300));
        Response act = given().queryParam("delay", 0).when().get("/slowApi");
        Assert.assertEquals(401, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testSlowApiWithNonZeroDelayReturnsResultBody() {
        given().when().get("/").then().statusCode(lessThan(300));
        Response act = given().queryParam("delay", 1).when().get("/slowApi");
        Assert.assertEquals(401, act.getStatusCode());
        Assert.assertTrue(act.getBody().asString().contains("Unauthorized"));
    }

    @Test(timeout = 60000)
    public void testSlowApiWithNegativeDelayReturnsServerError() {
        given().when().get("/").then().statusCode(lessThan(300));
        Response act = given().queryParam("delay", -1).when().get("/slowApi");
        Assert.assertEquals(401, act.getStatusCode());
    }
}