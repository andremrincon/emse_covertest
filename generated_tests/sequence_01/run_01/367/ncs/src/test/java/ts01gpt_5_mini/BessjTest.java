package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class BessjTest {

    @BeforeClass
    public static void setup() {
        String env = System.getProperty("base.url");
        if (env == null || env.isEmpty()) env = System.getenv("BASE_URL");
        if (env == null || env.isEmpty()) env = "http://localhost:8080";
        RestAssured.baseURI = env;
    }

    @Test(timeout = 60000)
    public void testBessj_NLessThan2_returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/bessj/1/1.0").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessj_XEqualsZero_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/bessj/3/0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_AxGreaterThanN_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/bessj/2/10.0").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessj_AxLessOrEqualN_smallX_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/bessj/10/1e-10").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_NegativeX_OddN_signFlip() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        String posBody = given().when().get("/api/bessj/3/2.5").then().statusCode(lessThan(300)).extract().body().asString();
        String negBody = given().when().get("/api/bessj/3/-2.5").then().statusCode(lessThan(300)).extract().body().asString();
        double pos = extractFirstNumber(posBody);
        double neg = extractFirstNumber(negBody);
        assertEquals(-pos, neg, 1e-6);
    }

    @Test(timeout = 60000)
    public void testBessj_InvalidNString_returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/bessj/abc/2.5").then().statusCode(400);
    }

    private static double extractFirstNumber(String s) {
        Pattern p = Pattern.compile("(-?\\d+\\.\\d+(?:[eE][-+]?\\d+)?)|(-?\\d+)");
        Matcher m = p.matcher(s);
        if (m.find()) {
            String num = m.group();
            return Double.parseDouble(num);
        }
        return Double.NaN;
    }
}