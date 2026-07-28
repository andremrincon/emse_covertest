package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import static org.hamcrest.Matchers.*;

public class FisherTest {

    @BeforeClass
    public static void setup() {
        String url = System.getProperty("baseUrl");
        if (url == null || url.isEmpty()) url = System.getenv("BASE_URL");
        if (url == null || url.isEmpty()) url = "http://localhost:8080";
        RestAssured.baseURI = url;
    }

    @Test(timeout = 60000)
    public void testFisher_bothOdd_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/fisher/5/3/0.75").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_aOdd_bEven_returns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        given().when().get("/api/fisher/5/4/0.75").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_aEven_bOdd_returns200() {
        given().when().get("/api/expint/3/1").then().statusCode(lessThan(300));
        given().when().get("/api/fisher/6/5/0.75").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_bothEven_returns200() {
        given().when().get("/api/bessj/3/2.5").then().statusCode(lessThan(300));
        given().when().get("/api/fisher/6/4/0.75").then().statusCode(200);
    }
}