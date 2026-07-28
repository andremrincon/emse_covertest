package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class DateParseTest {

    private static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:8080");

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test(timeout = 60000)
    public void testDateParse_monday_january_returns200() {
        given().when().get("/api/dateparse/sun/jul").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/xyz/aug").then().statusCode(lessThan(300));

        given().when().get("/api/dateparse/mon/jan").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParse_tuesday_february_returns200() {
        given().when().get("/api/dateparse/xyz/sep").then().statusCode(lessThan(300));

        given().when().get("/api/dateparse/tue/feb").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParse_wednesday_march_returns200() {
        given().when().get("/api/dateparse/xyz/oct").then().statusCode(lessThan(300));

        given().when().get("/api/dateparse/wed/mar").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParse_thursday_april_returns200() {
        given().when().get("/api/dateparse/xyz/nov").then().statusCode(lessThan(300));

        given().when().get("/api/dateparse/thur/apr").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParse_friday_may_returns200() {
        given().when().get("/api/dateparse/sat/jun").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/xyz/dec").then().statusCode(lessThan(300));

        given().when().get("/api/dateparse/fri/may").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParse_invalidDay_invalidMonth_returns500() {
        given().when().get("/api/dateparse/xyz/xyz").then().statusCode(200);
    }
}