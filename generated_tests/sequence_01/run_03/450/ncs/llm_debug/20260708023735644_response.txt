package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.nullValue;

public class FisherTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("ncs.baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("NCS_BASE_URL");
        if (base == null || base.isEmpty()) base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testFisher_bothOdd_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/fisher/{m}/{n}/{x}", 11, 5, 0.75);
        r.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_a1_bNot1_valuePresent() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/fisher/{m}/{n}/{x}", 9, 6, 0.5);
        r.then().body("value", nullValue());
    }

    @Test(timeout = 60000)
    public void testFisher_aNot1_b1_returns200() {
        given().when().get("/api/expint/3/2.5").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/fisher/{m}/{n}/{x}", 10, 7, 0.75);
        r.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_bothEven_returns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/fisher/{m}/{n}/{x}", 10, 6, 0.75);
        r.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_extremelyLargeX_returns200() {
        given().when().get("/api/bessj/3/2.5").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/fisher/{m}/{n}/{x}", 1, 1, 1e10);
        r.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_largeRatio_returns200() {
        given().when().get("/api/gammq/5.5/2.3").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/fisher/{m}/{n}/{x}", 2, 100, 1e6);
        r.then().statusCode(200);
    }
}