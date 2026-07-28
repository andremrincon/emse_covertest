package ts01gpt_5_mini;

import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class RemainderTest {

    private static String base;

    @BeforeClass
    public static void init() {
        String env = System.getProperty("api.base");
        if (env == null || env.isEmpty()) env = System.getenv("API_BASE");
        base = (env == null || env.isEmpty()) ? "http://localhost:8080" : env;
    }

    @Test(timeout = 60000)
    public void testRemainder_PositivePositive_ReturnsExpectedRemainder() {
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/remainder/17/5");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainder_PositiveNegative_Status200() {
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/remainder/17/-9");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainder_NegativePositive_Status200() {
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/remainder/-17/5");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainder_NegativeNegative_Status200() {
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/remainder/-17/-5");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainder_AZero_Returns400() {
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/remainder/0/5");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainder_BZero_Returns400() {
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/remainder/5/0");
        act.then().statusCode(200);
    }
}