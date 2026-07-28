package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ExpintTest {
    private static String base;

    @BeforeClass
    public static void setup() {
        String env = System.getProperty("api.base");
        if (env == null || env.isEmpty()) env = System.getenv("API_BASE");
        if (env == null || env.isEmpty()) env = "http://localhost:8080";
        base = env;
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testExpintContinuedFractionSuccess() {
        given().when().get(base + "/api/triangle/1/1/1").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/expint/3/2.5");
        Assert.assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testExpintSeriesSuccess() {
        given().when().get(base + "/api/triangle/1/1/1").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/expint/3/0.1");
        Assert.assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testExpintNZeroReturnsExpOverX() {
        given().when().get(base + "/api/triangle/1/1/1").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/expint/0/1.0");
        Assert.assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testExpintXZeroNm1Case() {
        given().when().get(base + "/api/triangle/1/1/1").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/expint/3/0");
        Assert.assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testExpintNegativeNBadRequest() {
        given().when().get(base + "/api/triangle/1/1/1").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/expint/-1/1.0");
        Assert.assertEquals(400, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testExpintNegativeXBadRequest() {
        given().when().get(base + "/api/triangle/1/1/1").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/expint/3/-0.5");
        Assert.assertEquals(400, act.getStatusCode());
    }
}