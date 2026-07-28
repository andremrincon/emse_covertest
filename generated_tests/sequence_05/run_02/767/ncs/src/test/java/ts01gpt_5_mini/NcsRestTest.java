package ts01gpt_5_mini;

import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class NcsRestTest {

    private static final String BASE = System.getProperty("API_BASE_URL", System.getenv("API_BASE_URL") == null ? "http://localhost:8080" : System.getenv("API_BASE_URL"));

    @Test(timeout = 60000)
    public void testFisherReturns200ForValidParameters() {
        given().when().get(BASE + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response response = given().when().get(BASE + "/api/fisher/10/5/0.75");
        Assert.assertEquals(200, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFisherReturns400WhenFisherThrowsRuntimeException() {
        given().when().get(BASE + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response response = given().when().get(BASE + "/api/fisher/10/5/1.2");
        Assert.assertEquals(200, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFisherReturns400WhenParametersExceedLimit() {
        given().when().get(BASE + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response response = given().when().get(BASE + "/api/fisher/1001/5/0.75");
        Assert.assertEquals(400, response.getStatusCode());
    }
}