package ts01gpt_5_mini;

import org.junit.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ExpintTest {

    private String base() {
        String p = System.getProperty("baseUrl");
        if (p != null && !p.isEmpty()) return p;
        String e = System.getenv("BASE_URL");
        if (e != null && !e.isEmpty()) return e;
        return "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testExpint_ContinuedFractionSuccess() {
        String b = base();
        given().when().get(b + "/api/remainder/17/5").then().statusCode(lessThan(300));
        given().when().get(b + "/api/expint/3/2.5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_SeriesSuccessSmallX() {
        String b = base();
        given().when().get(b + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get(b + "/api/expint/3/0.1").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_NZeroCase() {
        String b = base();
        given().when().get(b + "/api/fisher/10/5/0.75").then().statusCode(lessThan(300));
        given().when().get(b + "/api/expint/0/1.0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_XZero_NGreaterThanOne() {
        String b = base();
        given().when().get(b + "/api/gammq/5.5/2.3").then().statusCode(lessThan(300));
        given().when().get(b + "/api/expint/3/0.0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_NegativeX_BadRequest() {
        String b = base();
        given().when().get(b + "/api/remainder/17/5").then().statusCode(lessThan(300));
        given().when().get(b + "/api/expint/3/-1.0").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpint_XZero_NEqualsOne_BadRequest() {
        String b = base();
        given().when().get(b + "/api/remainder/5/4").then().statusCode(lessThan(300));
        given().when().get(b + "/api/expint/1/0.0").then().statusCode(400);
    }
}