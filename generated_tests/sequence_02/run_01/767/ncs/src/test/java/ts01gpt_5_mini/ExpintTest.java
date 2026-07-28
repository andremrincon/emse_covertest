package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.containsString;

public class ExpintTest {

    @BeforeClass
    public static void setup() {
        String url = System.getProperty("baseUrl");
        if (url == null || url.isEmpty()) {
            url = System.getenv("BASE_URL");
            if (url == null || url.isEmpty()) {
                url = "http://localhost:8080";
            }
        }
        RestAssured.baseURI = url;
    }

    @Test(timeout = 60000)
    public void testExpint_NegativeN_ShouldReturn400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/expint/-1/1.0").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpint_NEqualsZero_ShouldReturnResultField() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/expint/0/0.5").then().body(containsString("result"));
    }

    @Test(timeout = 60000)
    public void testExpint_XZero_NGreaterThanOne_ShouldReturnResultField() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/expint/3/0").then().body(containsString("result"));
    }

    @Test(timeout = 60000)
    public void testExpint_XGreaterThanOne_ContinuedFractionBranch_ShouldReturn200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/expint/3/2.5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_SeriesBranch_SmallX_ShouldReturn200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/expint/3/0.1").then().statusCode(200);
    }
}