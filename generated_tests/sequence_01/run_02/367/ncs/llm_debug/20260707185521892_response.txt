package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FisherTest {

    private static String baseUrl;

    @BeforeClass
    public static void setup() {
        baseUrl = System.getProperty("api.base.url", System.getenv().getOrDefault("BASE_URL", "http://localhost:8080"));
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test(timeout = 60000)
    public void testOddM_OddN_Returns200() {
        given().when().get(baseUrl + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get(baseUrl + "/api/fisher/11/5/0.75").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testOddM_EvenN_Returns200() {
        given().when().get(baseUrl + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get(baseUrl + "/api/fisher/11/4/0.75").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvenM_OddN_Returns200() {
        given().when().get(baseUrl + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get(baseUrl + "/api/fisher/10/5/0.75").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvenM_EvenN_Returns200() {
        given().when().get(baseUrl + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get(baseUrl + "/api/fisher/10/4/0.75").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testClippedToZeroWhenNegativeP_Returns200() {
        given().when().get(baseUrl + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get(baseUrl + "/api/fisher/2/2/-0.5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testClippedToOneWhenPGreaterThanOne_Returns200() {
        given().when().get(baseUrl + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get(baseUrl + "/api/fisher/2/2/-2.0").then().statusCode(200);
    }
}