package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FisherTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("ncs.base");
        if (base == null || base.isEmpty()) base = System.getenv("NCS_BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testFisher_OddOdd_Returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/fisher/7/5/0.75");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_OddEven_Returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/fisher/5/4/0.75");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_EvenOdd_Returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/fisher/10/5/0.75");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_EvenEven_Returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/fisher/10/4/0.75");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_XZero_Returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/fisher/3/5/0.0");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_InvalidParameter_Returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/fisher/abc/5/0.75");
        act.then().statusCode(400);
    }
}