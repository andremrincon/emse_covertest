package ts01gpt_5_mini_2;

import io.restassured.response.Response;
import org.junit.Test;
import org.junit.Assert;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class TriangleClassificationTest {

    private static final String BASE = System.getProperty("base.url", "http://localhost:8080");

    @Test(timeout = 60000)
    public void testTriangleInvalidWhenNonPositiveEdgeReturns200() {
        given().when().get(BASE + "/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/triangle/0/5/5");
        Assert.assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testTriangleEquilateralReturns200() {
        given().when().get(BASE + "/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/triangle/4/4/4");
        Assert.assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testTriangleDegenerateMaxGreaterOrEqualReturns200() {
        given().when().get(BASE + "/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/triangle/10/3/3");
        Assert.assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testTriangleIsoscelesReturns200() {
        given().when().get(BASE + "/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/triangle/5/5/3");
        Assert.assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testTriangleScaleneReturns200() {
        given().when().get(BASE + "/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/triangle/3/4/5");
        Assert.assertEquals(200, act.getStatusCode());
    }
}