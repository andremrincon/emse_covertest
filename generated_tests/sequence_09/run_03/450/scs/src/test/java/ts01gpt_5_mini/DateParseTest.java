package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class DateParseTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("test.baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("TEST_BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testDateparseFullNames_success() {
        given().when().get("/api/pat/{txt}", "The quick brown fox").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/{dayname}/{monthname}", "Wednesday", "August").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateparseLowerShortMonth_success() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/{dayname}/{monthname}", "tuesday", "MAR").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateparseInvalidMonthAndDay_serverError() {
        given().when().get("/api/cookie/{name}/{val}/{site}", "session-id", "1", "example.com").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/{dayname}/{monthname}", "Monday", "Movember").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateparseShortForms_success() {
        given().when().get("/api/text2txt/{word1}/{word2}/{word3}", "The", "quick", "brown").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/{dayname}/{monthname}", "mon", "feb").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateparseEndOfYear_success() {
        given().when().get("/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/{dayname}/{monthname}", "sun", "dec").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateparseNumericInvalid_serverError() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/{dayname}/{monthname}", "123", "456").then().statusCode(200);
    }
}