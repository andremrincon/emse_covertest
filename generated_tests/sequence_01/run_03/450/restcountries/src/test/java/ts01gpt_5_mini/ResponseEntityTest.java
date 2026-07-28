package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ResponseEntityTest {

    @BeforeClass
    public static void setup() {
        String env = System.getProperty("base.url", System.getenv().getOrDefault("BASE_URL", "http://localhost:8080/rest"));
        RestAssured.baseURI = env;
    }

    @Test(timeout = 60000)
    public void testV1NameNotFound_statusField() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/name/123");
        Assert.assertEquals(404, (int) act.jsonPath().getInt("status"));
    }

    @Test(timeout = 60000)
    public void testV1NameNotFound_messageField() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/name/123");
        Assert.assertEquals("Not Found", act.jsonPath().getString("message"));
    }

    @Test(timeout = 60000)
    public void testV1NameServerError_statusField() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/name/True");
        Assert.assertEquals(404, (int) act.jsonPath().getInt("status"));
    }

    @Test(timeout = 60000)
    public void testV1NameServerError_messageField() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/name/True");
        Assert.assertEquals("Not Found", act.jsonPath().getString("message"));
    }
}