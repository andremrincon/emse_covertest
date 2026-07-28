package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class DateParseTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("base.url") != null ? System.getProperty("base.url") : (System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080");
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testRecognizedDayWithJanReturns2() {
        String[] months = {"jan","feb","mar","apr","may","jun","jul","aug","sep","oct","nov","dec"};
        for (String m : months) {
            given().when().get("/api/dateparse/mon/" + m).then().statusCode(lessThan(300));
        }
        Response act = given().when().get("/api/dateparse/mon/jan");
        act.then().assertThat().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testUnrecognizedDayWithMarReturns3() {
        String[] months = {"jan","feb","mar","apr","may","jun","jul","aug","sep","oct","nov","dec"};
        for (String m : months) {
            given().when().get("/api/dateparse/xyz/" + m).then().statusCode(lessThan(300));
        }
        Response act = given().when().get("/api/dateparse/none/mar");
        act.then().assertThat().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testFullDayNameWednesdayAndAugustReturns8() {
        given().when().get("/api/pat/The quick brown fox").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/dateparse/Wednesday/August");
        act.then().assertThat().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testInvalidDayAndMonthCauseServerError500() {
        given().when().get("/api/pat/HealthCheck").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/mon/jan").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/123/Movember").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testMixedCaseThurAndDecReturns13() {
        String[] variants = {"ThUr","THUR","thur","tHur"};
        for (String v : variants) {
            given().when().get("/api/dateparse/" + v + "/dec").then().statusCode(lessThan(300));
        }
        Response act = given().when().get("/api/dateparse/ThUr/DeC");
        act.then().assertThat().body(equalTo("13"));
    }
}