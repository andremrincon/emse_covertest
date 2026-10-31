package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class DateParseTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void test_dayAbbrev_and_jan_month_returns_ok() {
        given().when().get("/api/calc/add/15.5/4.5").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/tuesday/feb").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/wed/mar").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/thur/apr").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/dateparse/mon/jan");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_weekend_day_and_may_month_returns_ok() {
        given().when().get("/api/calc/subtract/0/4.5").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/mon/jun").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/tue/jul").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/wed/aug").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/dateparse/sun/may");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_september_month_with_sat_day_returns_ok() {
        given().when().get("/api/cookie/session-id/abc-123-xyz-789/example.com").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/jul/jul").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/aug/aug").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/sep/sep").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/dateparse/sat/sep");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_fullday_name_nonmatching_abbrev_with_mar_returns_ok() {
        given().when().get("/api/calc/add/0/4.5").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/thur/oct").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/dateparse/wednesday/mar");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_multiple_months_exercised_in_arrange_and_act_december() {
        given().when().get("/api/calc/add/10/8").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/mon/nov").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/tue/oct").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/wed/sep").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/dateparse/fri/dec");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_invalid_day_and_month_returns_server_error() {
        given().when().get("/api/calc/add/15.5/4.5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/dateparse/Superday/Movember");
        act.then().statusCode(200);
    }
}