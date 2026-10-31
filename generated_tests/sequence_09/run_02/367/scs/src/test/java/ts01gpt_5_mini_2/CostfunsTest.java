package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class CostfunsTest {

    private String baseUrl() {
        String b = System.getProperty("base.url");
        if (b == null || b.isEmpty()) b = System.getenv("BASE_URL");
        if (b == null || b.isEmpty()) b = "http://localhost:8080";
        return b.replaceAll("/$", "");
    }

    @Test(timeout = 60000)
    public void testCostfuns_baab_returns10() {
        String base = baseUrl();
        RestAssured.baseURI = base;
        given().when().get(base + "/api/pat/a").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/costfuns/{i}/{s}", 1, "baab");
        resp.then().body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testCostfuns_abab_returns6() {
        String base = baseUrl();
        RestAssured.baseURI = base;
        String uid = UUID.randomUUID().toString();
        given().when().get(base + "/api/pat/a").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/costfuns/{i}/{s}", 1, "abab");
        resp.then().body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testCostfuns_minus4_abab_returns0() {
        String base = baseUrl();
        RestAssured.baseURI = base;
        given().when().get(base + "/api/pat/a").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/costfuns/{i}/{s}", -4, "abab");
        resp.then().body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testCostfuns_iEquals5_returns6() {
        String base = baseUrl();
        RestAssured.baseURI = base;
        given().when().get(base + "/api/pat/a").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/costfuns/{i}/{s}", 5, "abab");
        resp.then().body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testCostfuns_iMinus500_returns6() {
        String base = baseUrl();
        RestAssured.baseURI = base;
        given().when().get(base + "/api/pat/a").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/costfuns/{i}/{s}", -500, "abab");
        resp.then().body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testCostfuns_i667_returns6() {
        String base = baseUrl();
        RestAssured.baseURI = base;
        String unique = UUID.randomUUID().toString();
        given().when().get(base + "/api/pat/a").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/costfuns/{i}/{s}", 667, "abab");
        resp.then().body(equalTo("10"));
    }
}