package ts01gpt_5_mini;

import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.Optional;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FisherTest {

    private static String base;

    @BeforeClass
    public static void setup() {
        base = Optional.ofNullable(System.getenv("BASE_URL"))
                .orElse(Optional.ofNullable(System.getProperty("base.url")).orElse("http://localhost:8080"));
    }

    @Test(timeout = 60000)
    public void testFisher_bothOdd_shouldReturn200() {
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/fisher/5/5/0.75");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_mOdd_nEven_shouldReturn200() {
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/fisher/5/4/0.75");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_mEven_nOdd_shouldReturn200() {
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/fisher/6/5/0.75");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_bothEven_shouldReturn200() {
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/fisher/6/4/0.75");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_largeX_shouldReturn200() {
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/fisher/1/1/1000000");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_zeroX_shouldReturn200() {
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/fisher/2/2/0.0");
        act.then().statusCode(200);
    }
}