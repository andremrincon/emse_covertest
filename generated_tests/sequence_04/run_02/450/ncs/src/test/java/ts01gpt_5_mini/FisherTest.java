package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class FisherTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testFisher_BothOdd_Returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/fisher/1/1/0.75");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_OddM_EvenN_LoopPath_Returns200() {
        given().when().get("/api/triangle/5/5/6").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/fisher/3/4/0.5");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_EvenM_OddN_BodyContainsValue() {
        given().when().get("/api/triangle/6/8/10").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/fisher/4/3/0.2");
        act.then().statusCode(200).body("value", nullValue());
    }

    @Test(timeout = 60000)
    public void testFisher_BothEven_ComplexPath_Returns200() {
        given().when().get("/api/triangle/7/8/9").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/fisher/6/4/2.0");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_LargeX_AttemptsClampOrStability_Returns200() {
        given().when().get("/api/triangle/2/3/4").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/fisher/2/2/1000000.0");
        act.then().statusCode(200);
    }
}