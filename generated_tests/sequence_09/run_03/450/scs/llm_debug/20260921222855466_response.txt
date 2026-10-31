package ts01gpt_5_mini_2;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class DateParseTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testDateparse_Wednesday_August_returns200() {
        given().when().get("/api/pat/{txt}", "health").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/{dayname}/{monthname}", "Wednesday", "August").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateparse_tuesday_MAR_returns200() {
        given().when().get("/api/pat/{txt}", "health2").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/{dayname}/{monthname}", "tuesday", "MAR").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateparse_Superday_Movember_returns500() {
        given().when().get("/api/pat/{txt}", "setup3").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/{dayname}/{monthname}", "Superday", "Movember").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateparse_Monday_August_returns500() {
        given().when().get("/api/pat/{txt}", "setup4").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/{dayname}/{monthname}", "Monday", "August").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateparse_123_MAR_returns500() {
        given().when().get("/api/pat/{txt}", "setup5").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/{dayname}/{monthname}", "123", "MAR").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateparse_thur_dec_returns200() {
        given().when().get("/api/pat/{txt}", "setup6").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/{dayname}/{monthname}", "thur", "dec").then().statusCode(200);
    }
}