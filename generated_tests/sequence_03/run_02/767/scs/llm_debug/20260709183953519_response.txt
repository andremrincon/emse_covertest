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
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testIncreasingOrderProduces200() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "health-" + uuid).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "aaaaa", "bbbbb", "ddddd", "ccccc");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDecreasingOrderProduces200() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "health-" + uuid).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "ddddd", "ccccc", "aaaaa", "bbbbb");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedByComparisonProduces200() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "health-" + uuid).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "aaaaa", "ddddd", "ccccc", "bbbbb");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedByLengthProduces200() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "health-" + uuid).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "abcd", "bbbbb", "ddddd", "ccccc");
        act.then().statusCode(200);
    }
}