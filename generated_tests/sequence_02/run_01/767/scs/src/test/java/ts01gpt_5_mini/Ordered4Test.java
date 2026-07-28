package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class Ordered4Test {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void test_increasing_returns_increasing() {
        String rnd = UUID.randomUUID().toString();
        given().when().get("/api/pat/" + rnd).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/ordered4/alpha/bravo/delta/charl");
        act.then().body(equalTo("increasing"));
    }

    @Test(timeout = 60000)
    public void test_decreasing_returns_decreasing() {
        String rnd = UUID.randomUUID().toString();
        given().when().get("/api/pat/" + rnd).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/ordered4/omega/kappa/alpha/delta");
        act.then().body(equalTo("decreasing"));
    }

    @Test(timeout = 60000)
    public void test_unordered_when_lengths_not_in_range_returns_unordered() {
        String rnd = UUID.randomUUID().toString();
        given().when().get("/api/pat/" + rnd).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/ordered4/toolong/bravo/delta/charl");
        act.then().body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void test_unordered_when_lengths_ok_but_not_ordered_returns_unordered() {
        String rnd = UUID.randomUUID().toString();
        given().when().get("/api/pat/" + rnd).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/ordered4/alpha/charl/bravo/delta");
        act.then().body(equalTo("unordered"));
    }
}