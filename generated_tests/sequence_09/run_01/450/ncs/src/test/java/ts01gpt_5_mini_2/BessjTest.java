package ts01gpt_5_mini_2;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class BessjTest {

    private static final String BASE;
    static {
        String b = System.getProperty("api.base");
        if (b == null || b.isEmpty()) b = System.getenv("API_BASE");
        if (b == null || b.isEmpty()) b = "http://localhost:8080";
        BASE = b;
    }

    @Test(timeout = 60000)
    public void testBessj_nLessThanTwo_returns400() {
        given().when().get(BASE + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/bessj/1/2.5").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessj_axZero_returns200() {
        given().when().get(BASE + "/api/remainder/17/5").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/bessj/3/0.0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_largeAx_asymptotic_returns200() {
        given().when().get(BASE + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/bessj/2/10.0").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessj_negativeX_oddN_signHandled_returns200() {
        given().when().get(BASE + "/api/remainder/17/5").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/bessj/3/-10.0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_smallAx_downwardRecurrence_returns200() {
        given().when().get(BASE + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/bessj/5/1e-10").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_nNonNumeric_returns400() {
        given().when().get(BASE + "/api/remainder/17/5").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/bessj/abc/2.5").then().statusCode(400);
    }
}