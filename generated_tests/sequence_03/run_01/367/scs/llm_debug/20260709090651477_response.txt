package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class Ordered4Test {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("API_BASE");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("api.base", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void test_increasing_order_returns_increasing() {
        given().when().get("/api/pat/{txt}", "Ready-"+ UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "apple", "banan", "delta", "candy");
        act.then().body(equalTo("increasing"));
    }

    @Test(timeout = 60000)
    public void test_decreasing_order_returns_decreasing() {
        given().when().get("/api/pat/{txt}", "Prep-"+ UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "delta", "charl", "alpha", "bravo");
        act.then().body(equalTo("decreasing"));
    }

    @Test(timeout = 60000)
    public void test_same_lengths_but_unordered_returns_unordered() {
        given().when().get("/api/pat/{txt}", "Setup-"+ UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "apple", "candy", "delta", "banan");
        act.then().body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void test_out_of_range_length_returns_unordered() {
        given().when().get("/api/pat/{txt}", "Init-"+ UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "toolongg", "banan", "candy", "delta");
        act.then().body(equalTo("unordered"));
    }
}