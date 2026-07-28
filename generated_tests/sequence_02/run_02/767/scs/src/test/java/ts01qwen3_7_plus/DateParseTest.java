package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class DateParseTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null) {
            baseUrl = System.getProperty("base.url", "http://localhost:8080");
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testDateParseJan() {
        given().when().get("/api/dateparse/mon/jan").then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testDateParseFeb() {
        given().when().get("/api/dateparse/mon/feb").then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testDateParseMar() {
        given().when().get("/api/dateparse/mon/mar").then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testDateParseApr() {
        given().when().get("/api/dateparse/mon/apr").then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testDateParseMay() {
        given().when().get("/api/dateparse/mon/may").then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testDateParseInvalidDay() {
        given().when().get("/api/dateparse/Superday/jun").then().statusCode(200);
    }
}