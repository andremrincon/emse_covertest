package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FisherTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testFisher_BothOdd_Returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/fisher/3/5/0.75");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_MOdd_NEven_Returns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/fisher/3/4/0.75");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_MEven_NOdd_Returns200() {
        given().when().get("/api/bessj/3/2.5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/fisher/4/5/0.75");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_BothEven_Returns200() {
        given().when().get("/api/expint/3/2.5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/fisher/4/6/0.75");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_InvalidM_Returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/fisher/abc/5/0.75");
        resp.then().statusCode(400);
    }
}