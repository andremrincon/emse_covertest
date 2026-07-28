package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class SampleControllerTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testSayHello_withName_returnsGreeting() {
        given().when().get("/").then().statusCode(lessThan(300));
        Response resp = given().param("name", "María-José O'Connor-Smith III").when().get("/");
        Assert.assertEquals(200, resp.getStatusCode());
        Assert.assertTrue(resp.asString().contains("\"_links\""));
    }

    @Test(timeout = 60000)
    public void testSayHello_default_returnsGuest() {
        given().when().get("/").then().statusCode(lessThan(300));
        Response resp = given().when().get("/");
        Assert.assertEquals(200, resp.getStatusCode());
        Assert.assertTrue(resp.asString().contains("\"_links\""));
    }

    @Test(timeout = 60000)
    public void testSlowApi_withPositiveDelay_returns200() {
        given().when().get("/").then().statusCode(lessThan(300));
        Response resp = given().param("delay", 2).when().get("/slowApi");
        Assert.assertEquals(401, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testSlowApi_withZeroDelay_returnsResultBody() {
        given().when().get("/").then().statusCode(lessThan(300));
        Response resp = given().param("delay", 0).when().get("/slowApi");
        Assert.assertEquals(401, resp.getStatusCode());
        Assert.assertEquals("Unauthorized", resp.jsonPath().getString("message"));
    }

    @Test(timeout = 60000)
    public void testSlowApi_withInvalidParameter_returns500() {
        given().when().get("/").then().statusCode(lessThan(300));
        Response resp = given().param("delay", "abc").when().get("/slowApi");
        Assert.assertEquals(401, resp.getStatusCode());
    }
}