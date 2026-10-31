package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FisherTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("TEST_BASE_URL");
            if (base == null || base.isEmpty()) {
                base = "http://localhost:8080";
            }
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testFisher_oddM_oddN_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response response = given().when().get("/api/fisher/{m}/{n}/{x}", 11, 5, 0.75);
        Assert.assertEquals(200, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFisher_oddM_evenN_withZeroX_returns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response response = given().when().get("/api/fisher/{m}/{n}/{x}", 11, 6, 0.0);
        Assert.assertEquals(200, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFisher_evenM_oddN_returns200() {
        given().when().get("/api/expint/3/0.1").then().statusCode(lessThan(300));
        Response response = given().when().get("/api/fisher/{m}/{n}/{x}", 10, 5, 0.75);
        Assert.assertEquals(200, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFisher_evenM_evenN_returns200() {
        given().when().get("/api/bessj/3/2.5").then().statusCode(lessThan(300));
        Response response = given().when().get("/api/fisher/{m}/{n}/{x}", 10, 6, 0.0);
        Assert.assertEquals(200, response.getStatusCode());
    }
}