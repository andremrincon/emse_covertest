package ts01gpt_5_mini;

import io.restassured.response.Response;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class Ordered4Test {

    private static final String BASE = System.getProperty("baseUrl", System.getenv().getOrDefault("BASE_URL", "http://localhost:8080"));

    @Test(timeout = 60000)
    public void testIncreasingProducesIncreasingBody() {
        String uuid = UUID.randomUUID().toString();
        given().when().get(BASE + "/api/cookie/session-" + uuid + "/1/example.com").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/ordered4/aaaaa/bbbbb/dddddd/ccccc");
        act.then().body(equalTo("increasing"));
    }

    @Test(timeout = 60000)
    public void testDecreasingProducesDecreasingBody() {
        String uuid = UUID.randomUUID().toString();
        given().when().get(BASE + "/api/cookie/session-" + uuid + "/val/example.com").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/ordered4/zzzzz/yyyyy/wwwww/xxxxx");
        act.then().body(equalTo("decreasing"));
    }

    @Test(timeout = 60000)
    public void testUnorderedWhenLengthConstraintFails() {
        String uuid = UUID.randomUUID().toString();
        given().when().get(BASE + "/api/pat/" + uuid).then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/ordered4/abcd/bbbbb/ddddd/ccccc");
        act.then().body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testUnorderedWhenOrderDoesNotMatch() {
        String uuid = UUID.randomUUID().toString();
        given().when().get(BASE + "/api/costfuns/1/algorithm").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/ordered4/aaaaa/ccccc/ddddd/bbbbb");
        act.then().body(equalTo("unordered"));
    }
}