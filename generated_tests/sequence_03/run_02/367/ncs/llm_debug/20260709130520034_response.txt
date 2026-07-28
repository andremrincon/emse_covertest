package ts01gpt_5_mini;

import org.junit.Test;
import static org.hamcrest.Matchers.lessThan;
import static io.restassured.RestAssured.given;

public class BessjTest {

    private static String getBase() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        return base.endsWith("/") ? base.substring(0, base.length() - 1) : base;
    }

    @Test(timeout = 60000)
    public void testBessj_nLessThan2_returns400() {
        String base = getBase();
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get(base + "/api/bessj/1/2.5").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessj_xZero_returns200() {
        String base = getBase();
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get(base + "/api/bessj/3/0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_axGreaterThanN_smallAx_invokes_bessj0_bessj1_lt8() {
        String base = getBase();
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get(base + "/api/bessj/3/4.0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_axGreaterThanN_largeAx_invokes_bessj0_bessj1_ge8() {
        String base = getBase();
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get(base + "/api/bessj/3/10.0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_negativeX_oddN_returns200() {
        String base = getBase();
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get(base + "/api/bessj/3/-2.5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_verySmallX_triggers_series_path_returns200() {
        String base = getBase();
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get(base + "/api/bessj/10/1e-10").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_largeNegativeX_hits_bessj1_negative_branch_returns200() {
        String base = getBase();
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get(base + "/api/bessj/3/-10.0").then().statusCode(200);
    }
}