package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class DateParseTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testDayAndMonthAbbreviationsReturnSum() {
        String[] months = {"jan","feb","mar","apr","may","jun","jul","aug","sep","oct","nov","dec"};
        for (String m : months) {
            given().when().get("/api/dateparse/mon/" + m).then().statusCode(lessThan(300));
        }
        given().when().get("/api/pat/The").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/dateparse/mon/jan");
        resp.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testUnrecognizedDayWithDecemberReturnsTwelve() {
        given().when().get("/api/pat/The").then().statusCode(lessThan(300));
        String[] unDays = {"monday","superday","123"};
        for (String d : unDays) {
            given().when().get("/api/dateparse/" + d + "/dec").then().statusCode(lessThan(300));
        }
        Response resp = given().when().get("/api/dateparse/monday/dec");
        resp.then().body(equalTo("12"));
    }

    @Test(timeout = 60000)
    public void testMixedCaseDayAndMonthReturnsOkStatus() {
        given().when().get("/api/pat/The").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/TuE/Aug").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/dateparse/TuE/Aug");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnknownDayWithFebruaryReturnsTwo() {
        given().when().get("/api/pat/The").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/xyz/feb").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/dateparse/xyz/feb");
        resp.then().body(equalTo("2"));
    }

}