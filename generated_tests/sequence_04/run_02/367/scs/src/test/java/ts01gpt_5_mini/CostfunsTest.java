package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CostfunsTest {

    @BeforeClass
    public static void setup() {
        String env = System.getenv("API_BASE_URL");
        String prop = System.getProperty("api.base");
        String base = env != null ? env : (prop != null ? prop : "http://localhost:8080");
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testCostfuns_iEquals5_status200() {
        given().when().get("/api/calc/add/1/1").then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/{i}/{s}", 5, "test-string").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_iLessThanMinus444_status200() {
        given().when().get("/api/calc/add/2/3").then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/{i}/{s}", -500, "a").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_iLessEqualMinus333_status200() {
        given().when().get("/api/calc/add/3/4").then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/{i}/{s}", -333, "ababba").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_iGreaterThan666_status200() {
        given().when().get("/api/calc/add/5/6").then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/{i}/{s}", 667, "z").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_iGreaterOrEqual555_and_sEqualsConcat_status200() {
        given().when().get("/api/calc/add/7/8").then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/{i}/{s}", 555, "baab").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_sCompareTo_variants_status200() {
        given().when().get("/api/calc/add/9/10").then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/{i}/{s}", 0, "ababba").then().statusCode(200);
    }
}