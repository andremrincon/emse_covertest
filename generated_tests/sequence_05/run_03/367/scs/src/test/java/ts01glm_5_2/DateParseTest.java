package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class DateParseTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("BASE_URL");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testDateParseMonJan() {
        given().when().get("/api/dateparse/tue/jul").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/wed/aug").then().statusCode(lessThan(300));

        given().when().get("/api/dateparse/mon/jan").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseTueFeb() {
        given().when().get("/api/dateparse/thur/sep").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/fri/oct").then().statusCode(lessThan(300));

        given().when().get("/api/dateparse/tue/feb").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseWedMar() {
        given().when().get("/api/dateparse/sat/nov").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/sun/dec").then().statusCode(lessThan(300));

        given().when().get("/api/dateparse/wed/mar").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseThurApr() {
        given().when().get("/api/dateparse/mon/jun").then().statusCode(lessThan(300));

        given().when().get("/api/dateparse/thur/apr").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseFriMay() {
        given().when().get("/api/dateparse/fri/may").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseInvalidDayInvalidMonth() {
        given().when().get("/api/dateparse/xyz/abc").then().statusCode(200);
    }
}