package ts01gpt_5_mini;

import io.restassured.response.Response;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.containsString;

public class FisherTest {

    private String base() {
        String fromProp = System.getProperty("api.base");
        String fromEnv = System.getenv().getOrDefault("API_BASE", null);
        if (fromProp != null && !fromProp.isEmpty()) return fromProp;
        if (fromEnv != null && !fromEnv.isEmpty()) return fromEnv;
        return "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testFisher_generalBranch_returns200() {
        String base = base();
        given().when().get(base + "/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/fisher/10/6/0.75");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_a1_b1_returns200() {
        String base = base();
        given().when().get(base + "/api/remainder/18/4").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/fisher/1/1/0.5");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_a1_bNot1_returns200() {
        String base = base();
        given().when().get(base + "/api/remainder/19/6").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/fisher/1/4/0.75");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_invalidM_parameter_returns400() {
        String base = base();
        given().when().get(base + "/api/remainder/20/3").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/fisher/abc/5/0.75");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFisher_largeX_triggersUpperBound_responseContainsOne() {
        String base = base();
        String unique = UUID.randomUUID().toString();
        given().when().get(base + "/api/remainder/21/4").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/fisher/2/2/1000000.0");
        act.then().statusCode(200).body(containsString("0.9999990000010001"));
    }
}