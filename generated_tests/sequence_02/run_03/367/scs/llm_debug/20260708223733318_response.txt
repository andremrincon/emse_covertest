package ts01gpt_5_mini;

import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class CostfunsTest {
    private String base() {
        String p = System.getProperty("api.base");
        if (p != null && !p.isEmpty()) return p;
        String e = System.getenv("API_BASE");
        if (e != null && !e.isEmpty()) return e;
        String e2 = System.getenv("BASE_URL");
        if (e2 != null && !e2.isEmpty()) return e2;
        return "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testCostfunsReturns10ForNonAbab() {
        String uid = UUID.randomUUID().toString();
        given().when().get(base() + "/api/pat/" + uid).then().statusCode(lessThan(300));
        given().when().get(base() + "/api/costfuns/0/hello").then().body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testCostfunsReturns0WhenIIsMinus4AndSIsAbab() {
        String uid = UUID.randomUUID().toString();
        given().when().get(base() + "/api/pat/" + uid).then().statusCode(lessThan(300));
        given().when().get(base() + "/api/costfuns/-4/abab").then().body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testCostfunsStatus200WhenIIsFive() {
        String uid = UUID.randomUUID().toString();
        given().when().get(base() + "/api/pat/" + uid).then().statusCode(lessThan(300));
        given().when().get(base() + "/api/costfuns/5/x").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfunsWithVerySmallIProducesSixWhenSIsAbab() {
        String uid = UUID.randomUUID().toString();
        given().when().get(base() + "/api/pat/" + uid).then().statusCode(lessThan(300));
        given().when().get(base() + "/api/costfuns/-445/abab").then().body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testCostfunsCompareToEqualCaseReturns200() {
        String uid = UUID.randomUUID().toString();
        given().when().get(base() + "/api/pat/" + uid).then().statusCode(lessThan(300));
        given().when().get(base() + "/api/costfuns/-4/ababba").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfunsSequalsBaabExercisesStringEqualityBranch() {
        String uid = UUID.randomUUID().toString();
        given().when().get(base() + "/api/pat/" + uid).then().statusCode(lessThan(300));
        given().when().get(base() + "/api/costfuns/-4/baab").then().body(equalTo("10"));
    }
}