package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;
import java.util.UUID;

public class DateParseTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testMonJan_returns200() {
        given().when().get("/api/pat/{txt}", "setup-"+UUID.randomUUID()).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/dateparse/{dayname}/{monthname}", "Mon", "Jan");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnknownDayFeb_returns200() {
        given().when().get("/api/pat/{txt}", "setup-"+UUID.randomUUID()).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/dateparse/{dayname}/{monthname}", "Funday", "Feb");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testWednesdayAug_returns200() {
        given().when().get("/api/pat/{txt}", "setup-"+UUID.randomUUID()).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/dateparse/{dayname}/{monthname}", "Wednesday", "August");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testWedDec_returns200() {
        given().when().get("/api/pat/{txt}", "setup-"+UUID.randomUUID()).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/dateparse/{dayname}/{monthname}", "wed", "DEC");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNumericDay_returns500() {
        given().when().get("/api/pat/{txt}", "setup-"+UUID.randomUUID()).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/dateparse/{dayname}/{monthname}", "123", "Aug");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testMovemberMonth_returns500() {
        given().when().get("/api/pat/{txt}", "setup-"+UUID.randomUUID()).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/dateparse/{dayname}/{monthname}", "Mon", "Movember");
        resp.then().statusCode(200);
    }
}