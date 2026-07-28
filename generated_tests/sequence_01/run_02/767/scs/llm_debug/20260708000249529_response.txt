package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import static org.hamcrest.Matchers.lessThan;

public class CostfunsTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testCostfuns_iEquals5_sAbab_status200() {
        given().when().get("/api/pat/test").then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/{i}/{s}", 5, "abab").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_iLessThanMinus444_sAbab_status200() {
        given().when().get("/api/pat/health").then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/{i}/{s}", -500, "abab").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_iGreaterThan666_sAbab_status200() {
        given().when().get("/api/pat/ok").then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/{i}/{s}", 700, "abab").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_iMinus4_sBaab_status200() {
        given().when().get("/api/pat/ping").then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/{i}/{s}", -4, "baab").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_iMinus4_sAbabba_status200() {
        given().when().get("/api/pat/sample").then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/{i}/{s}", -4, "ababba").then().statusCode(200);
    }
}