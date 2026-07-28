package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
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
    public void testFisher_when_mOdd_nOdd_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/fisher/{m}/{n}/{x}", 5, 3, 0.75).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_when_mOdd_nEven_returns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        given().when().get("/api/fisher/{m}/{n}/{x}", 5, 4, 0.75).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_when_mEven_nOdd_returns200() {
        given().when().get("/api/expint/3/2.5").then().statusCode(lessThan(300));
        given().when().get("/api/fisher/{m}/{n}/{x}", 4, 3, 0.75).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_when_mEven_nEven_withZeroX_returns200() {
        given().when().get("/api/gammq/5.5/2.3").then().statusCode(lessThan(300));
        given().when().get("/api/fisher/{m}/{n}/{x}", 6, 4, 0.0).then().statusCode(200);
    }
}