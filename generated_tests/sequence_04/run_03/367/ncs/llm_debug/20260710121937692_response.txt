package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FisherTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testFisher_BothOdd_Returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/fisher/11/5/0.75");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_MOdd_NEven_Returns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/fisher/9/4/0.75");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_MEven_NOdd_Returns200() {
        given().when().get("/api/expint/3/1").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/fisher/10/5/0.0");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_BothEven_Returns200() {
        given().when().get("/api/gammq/5.5/2.3").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/fisher/8/4/0.1");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_InvalidM_Returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/fisher/abc/5/0.75");
        act.then().statusCode(400);
    }
}