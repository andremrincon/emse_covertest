package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class DateParseTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testValidDayMonValidMonthJan() {
        given().when().get("/api/dateparse/tue/feb").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/wed/mar").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/thur/apr").then().statusCode(lessThan(300));

        given().when().get("/api/dateparse/mon/jan").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testValidDayFriValidMonthMay() {
        given().when().get("/api/dateparse/sat/jun").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/sun/jul").then().statusCode(lessThan(300));

        given().when().get("/api/dateparse/fri/may").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testValidMonthAugThroughDec() {
        given().when().get("/api/dateparse/mon/sep").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/mon/oct").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/mon/nov").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/mon/dec").then().statusCode(lessThan(300));

        given().when().get("/api/dateparse/mon/aug").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testInvalidDayValidMonthJan() {
        given().when().get("/api/dateparse/mon/jan").then().statusCode(lessThan(300));

        given().when().get("/api/dateparse/xyzday/jan").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testValidDayMonInvalidMonth() {
        given().when().get("/api/dateparse/tue/feb").then().statusCode(lessThan(300));

        given().when().get("/api/dateparse/mon/xyzmonth").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testInvalidDayInvalidMonth() {
        given().when().get("/api/dateparse/mon/jan").then().statusCode(lessThan(300));

        given().when().get("/api/dateparse/xyzday/xyzmonth").then().statusCode(200);
    }
}