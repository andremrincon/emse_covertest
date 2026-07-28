package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import java.util.UUID;

public class CostfunsTest {

    @BeforeClass
    public static void setUp() {
        String env = System.getenv("API_BASE_URL");
        String prop = System.getProperty("api.base");
        if (env != null && !env.isEmpty()) {
            RestAssured.baseURI = env;
        } else if (prop != null && !prop.isEmpty()) {
            RestAssured.baseURI = prop;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testCostfuns_iEquals5_returns200() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/costfuns/{i}/{s}", 5, "arr-"+uid).then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/{i}/{s}", -500, "arr-"+uid).then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/{i}/{s}", -333, "arr-"+uid).then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/{i}/{s}", 667, "arr-"+uid).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", 5, "act-"+uid);
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testCostfuns_negativeAndEdgeIndexes_return200() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/costfuns/{i}/{s}", 555, "arr-"+uid).then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/{i}/{s}", 0, "arr-"+uid).then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/{i}/{s}", 5, "baab").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", -500, "neg-"+uid);
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testCostfuns_largeValues_greaterThan666_return200() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/costfuns/{i}/{s}", 667, "arr-"+uid).then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/{i}/{s}", 1000, "arr-"+uid).then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/{i}/{s}", -4, "arr-"+uid).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", 667, "big-"+uid);
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testCostfuns_stringCompareGreaterAndEquals_return200() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/costfuns/{i}/{s}", 0, "zzzz"+uid).then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/{i}/{s}", 0, "ababba").then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/{i}/{s}", 0, "baab").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", 0, "zzzz"+uid);
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testCostfuns_stringExactPattern_ababba_and_abab_variants_return200() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/costfuns/{i}/{s}", 1, "abab").then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/{i}/{s}", -4, "abab").then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/{i}/{s}", 5, "baab").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", 0, "abab");
        Assert.assertEquals(200, resp.getStatusCode());
    }
}