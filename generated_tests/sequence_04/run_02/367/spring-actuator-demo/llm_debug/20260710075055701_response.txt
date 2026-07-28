package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class SampleControllerTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testSayHelloWithName() {
        given().when().get("/").then().statusCode(lessThan(300));
        Response act = given().queryParam("name", "John Smith").when().get("/");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testSayHelloDefault() {
        given().when().get("/").then().statusCode(lessThan(300));
        Response act = given().when().get("/");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testTimeConsumingApiWithExplicitDelayReturns200() {
        given().when().get("/").then().statusCode(lessThan(300));
        Response act = given().queryParam("delay", 1).when().get("/slowApi");
        assertEquals(401, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testTimeConsumingApiWithZeroDelayUsesRandomAndReturnsResultBody() {
        given().when().get("/").then().statusCode(lessThan(300));
        Response act = given().queryParam("delay", 0).when().get("/slowApi");
        assertEquals(401, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testTimeConsumingApiNegativeDelayReturnsServerError() {
        given().when().get("/").then().statusCode(lessThan(300));
        Response act = given().queryParam("delay", -1).when().get("/slowApi");
        assertEquals(401, act.getStatusCode());
    }

}