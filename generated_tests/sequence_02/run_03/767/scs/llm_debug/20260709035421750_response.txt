package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class Ordered4Test {
    @BeforeClass
    public static void init() {
        String env = System.getProperty("api.base");
        if (env == null || env.isEmpty()) {
            String e = System.getenv("API_BASE");
            if (e == null || e.isEmpty()) {
                env = "http://localhost:8080";
            } else {
                env = e;
            }
        }
        RestAssured.baseURI = env;
    }

    @Test(timeout = 60000)
    public void testIncreasingOrderProduces200() {
        given().when().get("/api/pat/{txt}", UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        Response r = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "aaaaa", "bbbbb", "ddddd", "ccccc");
        assertEquals(200, r.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testDecreasingOrderProduces200() {
        given().when().get("/api/pat/{txt}", UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        Response r = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "zzzzz", "yyyyy", "wwwww", "xxxxx");
        assertEquals(200, r.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testValidLengthsButUnorderedProduces200() {
        given().when().get("/api/pat/{txt}", UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        Response r = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "apple", "mango", "pearl", "zebra");
        assertEquals(200, r.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testInvalidLengthProduces200() {
        given().when().get("/api/pat/{txt}", UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        Response r = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "a", "bbbbb", "ccccc", "ddddd");
        assertEquals(200, r.getStatusCode());
    }
}