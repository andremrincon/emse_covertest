package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class NcsRestTest {

    private static String base;

    @BeforeClass
    public static void init() {
        base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testBessj_valid() {
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/bessj/3/2.5");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_invalidN() {
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/bessj/2/1.0");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessj_edgeXSmall() {
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/bessj/3/" + Double.toString(1e-10));
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_valid() {
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/fisher/10/5/0.75");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_invalidHigh() {
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/fisher/1001/5/0.75");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFisher_runtimeException() {
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/fisher/10/5/1.2");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_valid() {
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/gammq/5.5/2.3");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_invalidA() {
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/gammq/-1.0/2.0");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammq_runtimeException() {
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/gammq/5.5/3.0");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainder_valid() {
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/remainder/17/5");
        act.then().body("resultAsInt", equalTo(2));
    }

    @Test(timeout = 60000)
    public void testRemainder_invalidLimit() {
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/remainder/10001/1");
        act.then().statusCode(400);
    }
}