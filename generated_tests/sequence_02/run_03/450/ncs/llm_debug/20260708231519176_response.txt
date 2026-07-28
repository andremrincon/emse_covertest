package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FisherTest {

    @BeforeClass
    public static void setup() {
        String envUrl = System.getProperty("baseUrl", System.getenv().getOrDefault("BASE_URL", "http://localhost:8080"));
        RestAssured.baseURI = envUrl;
    }

    @Test(timeout = 60000)
    public void testFisher_bothOdd_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/fisher/3/3/0.75").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_mOdd_nEven_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/fisher/3/4/0.75").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_mEven_nOdd_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/fisher/4/3/0.75").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_bothEven_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/fisher/4/2/0.75").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_zeroX_triggersZEqualsOne_path_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/fisher/4/3/0.0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_largeM_stressLoops_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/fisher/101/5/0.9").then().statusCode(200);
    }
}