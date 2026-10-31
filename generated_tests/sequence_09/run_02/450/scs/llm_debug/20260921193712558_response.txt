package ts01gpt_5_mini_3;

import org.junit.Test;
import org.junit.BeforeClass;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;
import java.util.UUID;

public class CostfunsTest {

    private static String base;

    @BeforeClass
    public static void setup() {
        String env = System.getenv("API_BASE");
        String prop = System.getProperty("api.base");
        if (env != null && !env.isEmpty()) {
            base = env;
        } else if (prop != null && !prop.isEmpty()) {
            base = prop;
        } else {
            base = "http://localhost:8080";
        }
        baseURI = base;
    }

    @Test(timeout = 60000)
    public void test_costfuns_with_i1_algorithm_returns200() {
        String name = "n-" + UUID.randomUUID().toString();
        given().when().get("/api/cookie/{name}/{val}/{site}", name, "1", "localhost").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", 1, "algorithm");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_costfuns_with_i0_abab_returns200() {
        String name = "n-" + UUID.randomUUID().toString();
        given().when().get("/api/cookie/{name}/{val}/{site}", name, "abc-123", "example.com").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", 0, "abab");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_costfuns_with_negative500_zzzz_returns200() {
        String name = "n-" + UUID.randomUUID().toString();
        given().when().get("/api/cookie/{name}/{val}/{site}", name, "val-"+UUID.randomUUID().toString(), "localhost").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", -500, "zzzz");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_costfuns_with_minus4_baab_returns200() {
        String name = "n-" + UUID.randomUUID().toString();
        given().when().get("/api/cookie/{name}/{val}/{site}", name, "1", "example.com").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", -4, "baab");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_costfuns_with_700_zzzz_returns200() {
        String name = "n-" + UUID.randomUUID().toString();
        given().when().get("/api/cookie/{name}/{val}/{site}", name, "v", "localhost").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", 700, "zzzz");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_costfuns_with_555_ababba_returns200() {
        String name = "n-" + UUID.randomUUID().toString();
        given().when().get("/api/cookie/{name}/{val}/{site}", name, "x", "example.com").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", 555, "ababba");
        resp.then().statusCode(200);
    }
}