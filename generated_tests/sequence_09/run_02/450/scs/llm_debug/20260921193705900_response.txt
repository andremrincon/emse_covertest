package ts01gpt_5_mini_3;

import org.junit.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class Ordered4Test {

    private static final String BASE = System.getProperty("API_BASE_URL",
            System.getenv().getOrDefault("API_BASE_URL", "http://localhost:8080"));

    @Test(timeout = 60000)
    public void testIncreasingOrdered4() {
        given().when().get(BASE + "/api/pat/The").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/ordered4/apple/baker/delta/candy");
        act.then().body(equalTo("increasing"));
    }

    @Test(timeout = 60000)
    public void testDecreasingOrdered4() {
        given().when().get(BASE + "/api/pat/The").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/ordered4/omega/noble/kappa/lambs");
        act.then().body(equalTo("decreasing"));
    }

    @Test(timeout = 60000)
    public void testUnorderedByLengthOrdered4() {
        given().when().get(BASE + "/api/pat/The").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/ordered4/a/short/short/short");
        act.then().body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testUnorderedBySequenceOrdered4() {
        given().when().get(BASE + "/api/pat/The").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/ordered4/baker/apple/delta/candy");
        act.then().body(equalTo("unordered"));
    }
}