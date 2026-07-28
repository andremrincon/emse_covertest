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
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testFisher_OddM_OddN_Returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/fisher/11/5/0.75");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_OddM_EvenN_Returns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/fisher/11/6/0.75");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_EvenM_OddN_Returns200() {
        given().when().get("/api/expint/3/2.5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/fisher/10/5/0.75");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_EvenM_EvenN_Returns200() {
        given().when().get("/api/gammq/5.5/2.3").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/fisher/10/6/0.75");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_InvalidM_Returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/fisher/abc/5/0.75");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFisher_ZeroX_Returns200() {
        given().when().get("/api/bessj/3/2.5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/fisher/11/5/0.0");
        resp.then().statusCode(200);
    }
}