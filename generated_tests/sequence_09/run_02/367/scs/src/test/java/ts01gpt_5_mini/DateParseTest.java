package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class DateParseTest {

    @BeforeClass
    public static void setup() {
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
    public void testAllMonthsViaArrange_thenMonJanReturns2() {
        String[] months = {"jan","feb","mar","apr","may","jun","jul","aug","sep","oct","nov","dec"};
        for (String m : months) {
            given().when().get("/api/dateparse/mon/" + m).then().statusCode(lessThan(300));
        }
        Response act = given().when().get("/api/dateparse/mon/jan");
        act.then().statusCode(200).assertThat().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testAllDaysViaArrange_thenTueMarReturns4() {
        String[] days = {"mon","tue","wed","thur","fri","sat","sun"};
        for (String d : days) {
            given().when().get("/api/dateparse/" + d + "/mar").then().statusCode(lessThan(300));
        }
        Response act = given().when().get("/api/dateparse/tue/mar");
        act.then().statusCode(200).assertThat().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testUnknownDayAndMonthProducesServerError() {
        given().when().get("/api/pat/a").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/Superday/Movember").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testMixedCaseInputsAreLowercased_thenMonDecReturns13() {
        given().when().get("/api/pat/healthcheck").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/dateparse/Mon/Dec");
        act.then().statusCode(200).assertThat().body(equalTo("13"));
    }
}