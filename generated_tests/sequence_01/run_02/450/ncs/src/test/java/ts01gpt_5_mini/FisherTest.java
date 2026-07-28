package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertNotNull;

public class FisherTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("API_BASE");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testFisher_bothOdd_returns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/fisher/{m}/{n}/{x}", 11, 5, 0.75);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_mOdd_nEven_returns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/fisher/{m}/{n}/{x}", 9, 4, 0.75);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_mEven_nOdd_returns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/fisher/{m}/{n}/{x}", 8, 5, 0.75);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_bothEven_returns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/fisher/{m}/{n}/{x}", 8, 4, 0.75);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_invalidM_returns400() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/fisher/{m}/{n}/{x}", "abc", 5, 0.75);
        act.then().statusCode(400);
    }
}