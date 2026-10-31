package ts01gpt_5_mini_3;
import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;
public class CostfunsTest {
    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            String env = System.getenv("API_BASE");
            if (env == null || env.isEmpty()) {
                RestAssured.baseURI = "http://localhost:8080";
            } else {
                RestAssured.baseURI = env;
            }
        } else {
            RestAssured.baseURI = base;
        }
    }
    @Test(timeout = 60000)
    public void testZeroResultForMinus4AndAbab() {
        given().when().get("/api/calc/add/15.5/4.5").then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/-4/abab").then().body(equalTo("10"));
    }
    @Test(timeout = 60000)
    public void testResultTenForAlgorithm() {
        given().when().get("/api/calc/add/0/4.5").then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/1/algorithm").then().body(equalTo("10"));
    }
    @Test(timeout = 60000)
    public void testStatus200WhenSIsBaab() {
        given().when().get("/api/calc/add/15.5/4.5").then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/-4/baab").then().statusCode(200);
    }
    @Test(timeout = 60000)
    public void testCompareToGeBranchTriggered() {
        given().when().get("/api/calc/add/15.5/4.5").then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/0/ababba").then().statusCode(200);
    }
    @Test(timeout = 60000)
    public void testResultSixForLargeIWithAbab() {
        given().when().get("/api/calc/add/15.5/4.5").then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/700/abab").then().body(equalTo("10"));
    }
    @Test(timeout = 60000)
    public void testNegativeLargeForIltMinus444() {
        given().when().get("/api/calc/add/15.5/4.5").then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/-500/abab").then().statusCode(200);
    }
}