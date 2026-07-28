package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class DateParseTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testValidDayThurAndMonthApr() {
        given().when().get("/api/dateparse/mon/jan").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/tue/feb").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/wed/mar").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/thur/apr").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testValidDayMonAndMonthAug() {
        given().when().get("/api/dateparse/fri/may").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/sat/jun").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/sun/jul").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/mon/aug").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testValidDayFriAndMonthDec() {
        given().when().get("/api/dateparse/tue/sep").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/wed/oct").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/thur/nov").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/fri/dec").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testInvalidDayAndInvalidMonth() {
        given().when().get("/api/dateparse/xyz/xyz").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testValidDayAndInvalidMonth() {
        given().when().get("/api/dateparse/mon/xyz").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testInvalidDayAndValidMonth() {
        given().when().get("/api/dateparse/xyz/jan").then().statusCode(200);
    }
}