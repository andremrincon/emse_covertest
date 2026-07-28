package ts01gpt_5_mini;

import org.junit.Test;
import io.restassured.response.Response;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class Ordered4Test {

    private String base() {
        String env = System.getenv("API_BASE");
        return env != null && !env.isEmpty() ? env : System.getProperty("api.base", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testIncreasingOrder() {
        String base = base();
        given().baseUri(base).when().get("/api/pat/" + UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        Response resp = given().baseUri(base).when().get("/api/ordered4/aaaaa/bbbbb/ccccc/ddddd");
        resp.then().assertThat().body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testDecreasingOrder() {
        String base = base();
        given().baseUri(base).when().get("/api/pat/" + UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        Response resp = given().baseUri(base).when().get("/api/ordered4/zzzzz/yyyyy/mmmmm/aaaaa");
        resp.then().assertThat().body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testLengthsOutOfRangeYieldUnordered() {
        String base = base();
        given().baseUri(base).when().get("/api/pat/" + UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        Response resp = given().baseUri(base).when().get("/api/ordered4/four/bbbbb/ccccc/ddddd");
        resp.then().assertThat().body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testEqualElementsProduceUnordered() {
        String base = base();
        given().baseUri(base).when().get("/api/pat/" + UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        Response resp = given().baseUri(base).when().get("/api/ordered4/apple/apple/ccccc/ddddd");
        resp.then().assertThat().body(equalTo("unordered"));
    }
}