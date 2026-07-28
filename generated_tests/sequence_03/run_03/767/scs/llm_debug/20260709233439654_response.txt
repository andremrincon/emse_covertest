package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class Ordered4Test {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = System.getProperty("baseUrl");
        }
        if (base == null || base.isEmpty()) {
            base = System.getenv("baseUrl");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testIncreasingOrderReturns200() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "health-"+uid).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "aaaaa", "bbbbb", "ddddd", "ccccc");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDecreasingOrderReturns200() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "setup-"+uid).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "zzzzz", "yyyyy", "wwwww", "xxxxx");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedDueToLengthConstraintReturns200() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "pre-"+uid).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "aaaaa", "bbbbb", "ccc", "ddddd");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedWhenComparisonsDoNotMatchReturns200() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "prep-"+uid).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "aaaaa", "ccccc", "bbbbb", "eeeee");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedWhenAllEqualReturns200() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "chk-"+uid).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "aaaaa", "aaaaa", "aaaaa", "aaaaa");
        resp.then().statusCode(200);
    }
}