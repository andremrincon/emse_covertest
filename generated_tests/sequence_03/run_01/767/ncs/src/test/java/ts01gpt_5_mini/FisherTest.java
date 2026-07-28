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
    public static void init() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testFisher_whenBothOdd_a1b1_status200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        String uuid = UUID.randomUUID().toString();
        Response r = given().when().get("/api/fisher/{m}/{n}/{x}", 1, 1, 0.75);
        r.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_whenMOdd_NEve_a1bNot1_status200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        String uuid = UUID.randomUUID().toString();
        Response r = given().when().get("/api/fisher/{m}/{n}/{x}", 5, 4, 0.75);
        r.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_whenMEven_NOdd_aNot1b1_status200() {
        given().when().get("/api/expint/3/2.5").then().statusCode(lessThan(300));
        String uuid = UUID.randomUUID().toString();
        Response r = given().when().get("/api/fisher/{m}/{n}/{x}", 10, 5, 0.75);
        r.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_whenBothEven_aNot1bNot1_status200() {
        given().when().get("/api/gammq/5.5/2.3").then().statusCode(lessThan(300));
        String uuid = UUID.randomUUID().toString();
        Response r = given().when().get("/api/fisher/{m}/{n}/{x}", 10, 6, 0.75);
        r.then().statusCode(200);
    }
}