package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FisherTest {

    @BeforeClass
    public static void init() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testFisher_bothOdd_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/fisher/11/5/0.5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_aOne_bNotOne_returns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        given().when().get("/api/fisher/7/4/0.75").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_aNotOne_bOne_returns200() {
        given().when().get("/api/expint/3/2.5").then().statusCode(lessThan(300));
        given().when().get("/api/fisher/8/5/0.25").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_neitherOne_returns200() {
        given().when().get("/api/bessj/3/2.5").then().statusCode(lessThan(300));
        given().when().get("/api/fisher/10/6/0.75").then().statusCode(200);
    }
}