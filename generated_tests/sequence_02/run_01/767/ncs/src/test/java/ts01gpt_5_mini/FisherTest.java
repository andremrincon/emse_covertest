package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FisherTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl", System.getenv("BASE_URL"));
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testFisher_mOdd_nOdd_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/fisher/1/1/0.5");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_mOdd_nEven_returns200() {
        String unique = UUID.randomUUID().toString();
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/fisher/1/2/0.75");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_mEven_nOdd_returns200() {
        given().when().get("/api/expint/3/2.5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/fisher/10/5/0.75");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_mEven_nEven_returns200() {
        given().when().get("/api/bessj/3/2.5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/fisher/10/4/0.0");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_invalidM_returns400() {
        given().when().get("/api/gammq/5.5/2.3").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/fisher/abc/5/0.75");
        resp.then().statusCode(400);
    }
}