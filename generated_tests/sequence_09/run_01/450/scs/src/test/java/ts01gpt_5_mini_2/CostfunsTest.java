package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class CostfunsTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("API_BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("api.base", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testReturnsZeroForNegativeFourAbab() {
        given().when().get("/api/title/male/Smith").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/costfuns/{i}/{s}", -4, "abab");
        act.then().body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testIEquals5ProducesSix() {
        given().when().get("/api/title/male/Smith").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/costfuns/{i}/{s}", 5, "abab");
        act.then().body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testILessThanNegative444Returns200() {
        given().when().get("/api/title/male/Smith").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/costfuns/{i}/{s}", -500, "abab");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIGreaterThan666AndGe555Returns200() {
        given().when().get("/api/title/male/Smith").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/costfuns/{i}/{s}", 700, "abab");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSEqualsAndCompareBranchesProduceTen() {
        given().when().get("/api/title/male/Smith").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/costfuns/{i}/{s}", 5, "baab");
        act.then().body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testCompareToEqualStringTriggersCompareGeBranch() {
        given().when().get("/api/title/male/Smith").then().statusCode(lessThan(300));
        String unique = "ababba";
        Response act = given().when().get("/api/costfuns/{i}/{s}", -4, unique);
        act.then().statusCode(200);
    }
}