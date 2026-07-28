package ts01gpt_5_mini;

import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.Optional;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class TriangleClassificationTest {

    private static String base;

    @BeforeClass
    public static void setup() {
        String prop = System.getProperty("baseUrl");
        String env = System.getenv("BASE_URL");
        base = Optional.ofNullable(prop).orElse(Optional.ofNullable(env).orElse("http://localhost:8080"));
    }

    @Test(timeout = 60000)
    public void testEquilateralTriangleReturns200() {
        given().when().get(base + "/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/triangle/3/3/3");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIsoscelesTriangleReturns200() {
        given().when().get(base + "/api/remainder/10/4").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/triangle/5/5/3");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testScaleneTriangleReturns200() {
        given().when().get(base + "/api/remainder/9/4").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/triangle/3/4/5");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNonPositiveSideReturns200() {
        given().when().get(base + "/api/remainder/7/3").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/triangle/0/4/5");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDegenerateTriangleEqualityReturns200() {
        given().when().get(base + "/api/remainder/11/6").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/triangle/5/2/3");
        act.then().statusCode(200);
    }
}