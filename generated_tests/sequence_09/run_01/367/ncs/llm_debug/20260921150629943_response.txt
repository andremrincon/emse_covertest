package ts01gpt_5_mini_2;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ExpintTest {

    private String base() {
        String b = System.getProperty("baseUrl");
        if (b != null && !b.isEmpty()) return b;
        b = System.getenv("BASE_URL");
        if (b != null && !b.isEmpty()) return b;
        return "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testExpintContinuedFraction() {
        String base = base();
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get(base + "/api/expint/3/2.5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintSeriesSmallX() {
        String base = base();
        given().when().get(base + "/api/remainder/17/5").then().statusCode(lessThan(300));
        given().when().get(base + "/api/expint/3/0.1").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintNZero() {
        String base = base();
        given().when().get(base + "/api/bessj/3/2.5").then().statusCode(lessThan(300));
        given().when().get(base + "/api/expint/0/2.5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintXZeroN2() {
        String base = base();
        given().when().get(base + "/api/fisher/10/5/0.75").then().statusCode(lessThan(300));
        given().when().get(base + "/api/expint/2/0.0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintInvalidNegativeN() {
        String base = base();
        given().when().get(base + "/api/triangle/1/1/1").then().statusCode(lessThan(300));
        given().when().get(base + "/api/expint/-1/1.0").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpintInvalidXZeroForNOne() {
        String base = base();
        given().when().get(base + "/api/gammq/5.5/2.3").then().statusCode(lessThan(300));
        given().when().get(base + "/api/expint/1/0.0").then().statusCode(400);
    }
}