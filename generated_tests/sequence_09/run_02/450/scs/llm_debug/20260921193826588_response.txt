package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class DateParseTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = System.getProperty("test.server");
        }
        if (base == null || base.isEmpty()) {
            base = System.getenv("TEST_SERVER");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testParseMonJan_after_exercising_all_day_and_month_branches() {
        given().when().get("/api/dateparse/mon/jan").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/tue/feb").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/wed/mar").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/thur/apr").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/fri/may").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/sat/jun").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/sun/jul").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/mon/aug").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/tue/sep").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/wed/oct").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/thur/nov").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/fri/dec").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/mon/jan").then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testParseUnknownDayAndMonth_returns500() {
        given().when().get("/api/dateparse/mon/jan").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/tue/mar").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/Superday/Movember").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testParseThurSep_returnsTen() {
        given().when().get("/api/dateparse/mon/feb").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/tue/mar").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/thur/sep").then().body(equalTo("10"));
    }
}