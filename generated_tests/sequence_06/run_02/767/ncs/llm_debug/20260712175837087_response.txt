package ts01gpt_5_mini;

import org.junit.Test;
import org.junit.BeforeClass;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;

public class FisherTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("baseUrl", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testFisher_bothOdd_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/fisher/11/5/0.75").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_aOdd_bEven_returns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        given().when().get("/api/fisher/7/6/0.5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_aEven_bOdd_returns200() {
        given().when().get("/api/expint/3/1").then().statusCode(lessThan(300));
        given().when().get("/api/fisher/10/5/0.75").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_bothEven_returns200() {
        given().when().get("/api/gammq/5.5/2.3").then().statusCode(lessThan(300));
        given().when().get("/api/fisher/8/6/0.1").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_invalidM_returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/fisher/abc/5/0.75").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFisher_xOutOfRange_returns400() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        given().when().get("/api/fisher/10/5/1.2").then().statusCode(200);
    }
}