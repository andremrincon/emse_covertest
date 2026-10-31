package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class CostfunsTest {

    @BeforeClass
    public static void setup() {
        String env = System.getenv("API_BASE_URL");
        if (env != null && !env.isEmpty()) {
            if (env.startsWith("http://") || env.startsWith("https://")) {
                RestAssured.baseURI = env;
            } else {
                RestAssured.baseURI = "http://" + env;
            }
        } else {
            String prop = System.getProperty("api.base");
            if (prop != null && !prop.isEmpty()) {
                RestAssured.baseURI = prop;
            } else {
                RestAssured.baseURI = "http://localhost:8080";
            }
        }
    }

    @Test(timeout = 60000)
    public void test_iEquals5_returns200() {
        given().when().get("/api/pat/The").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/5/algorithm");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void test_iLessThanNegative444_returns200() {
        given().when().get("/api/pat/a").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/-500/algorithm");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void test_iGreaterThan666_returns200() {
        given().when().get("/api/pat/The").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/700/algorithm");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void test_sEqualsConcatenation_executesBranch_returns200() {
        given().when().get("/api/pat/The").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/-4/baab");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void test_compareToBranches_return200() {
        given().when().get("/api/pat/The").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/-4/z");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void test_invalid_i_param_returns400() {
        given().when().get("/api/pat/The").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/one/algorithm");
        assertEquals(400, resp.getStatusCode());
    }
}