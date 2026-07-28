package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CostfunsTest {

    @BeforeClass
    public static void setUp() {
        String env = System.getenv("BASE_URL");
        if (env != null && !env.isEmpty()) {
            RestAssured.baseURI = env;
        } else {
            RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
        }
    }

    @Test(timeout = 60000)
    public void test_iEqualsFive_returns200() {
        given().when().get("/api/calc/add/15.5/4.5").then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/{i}/{s}", 5, "test").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_iLessThanMinus444_returns200() {
        given().when().get("/api/calc/add/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/{i}/{s}", -500, "zzz").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_iLessOrEqualMinus333_returns200() {
        given().when().get("/api/calc/add/1/1").then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/{i}/{s}", -333, "a").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_iGreaterThan666_returns200() {
        given().when().get("/api/calc/add/2/3").then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/{i}/{s}", 667, "zzzz").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_iEqualsMinusFour_branchFalse_returns200() {
        given().when().get("/api/calc/add/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/{i}/{s}", -4, "algo").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_stringEqualsBaab_and_comparePaths_returns200() {
        given().when().get("/api/calc/add/10/5").then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/{i}/{s}", 0, "baab").then().statusCode(200);
    }
}