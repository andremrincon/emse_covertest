package ts01glm_5_2;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;
import org.junit.Before;
import org.junit.Test;

public class DateParseTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv().getOrDefault("BASE_URL", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testDateParseMonJanTueFebWedMarThurAprFriMay() {
        given().when().get("/api/dateparse/mon/jan").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/tue/feb").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/wed/mar").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/thur/apr").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/fri/may").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseSatJunSunJulMonAugTueSepWedOct() {
        given().when().get("/api/dateparse/sat/jun").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/sun/jul").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/mon/aug").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/tue/sep").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/wed/oct").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseThurNovFriDecSatJan() {
        given().when().get("/api/dateparse/thur/nov").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/fri/dec").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/sat/jan").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseInvalidDayInvalidMonth() {
        given().when().get("/api/dateparse/xyz/xyz").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseValidDayInvalidMonth() {
        given().when().get("/api/dateparse/mon/xyz").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseInvalidDayValidMonth() {
        given().when().get("/api/dateparse/xyz/jan").then().statusCode(200);
    }
}