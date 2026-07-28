package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class DateParseTest {

    @BeforeClass
    public static void setup() {
        String url = System.getProperty("API_BASE_URL");
        if (url == null || url.isEmpty()) {
            url = System.getenv("API_BASE_URL");
        }
        if (url == null || url.isEmpty()) {
            url = "http://localhost:8080";
        }
        RestAssured.baseURI = url;
    }

    @Test(timeout = 60000)
    public void testParseJanReturnsTwo() {
        given().when().get("/api/dateparse/Mon/FEB").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/mon/MAR").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/dateparse/Mon/Jan");
        act.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testParseMarReturnsFour() {
        given().when().get("/api/dateparse/Wed/APR").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/wed/MAY").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/dateparse/wed/Mar");
        act.then().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testParseJunReturnsSeven() {
        given().when().get("/api/dateparse/Tue/JUN").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/tue/JUL").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/dateparse/tue/Jun");
        act.then().body(equalTo("7"));
    }

    @Test(timeout = 60000)
    public void testParseAugReturnsNine() {
        given().when().get("/api/dateparse/Fri/SEP").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/fri/OCT").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/dateparse/Fri/aug");
        act.then().body(equalTo("9"));
    }

    @Test(timeout = 60000)
    public void testParseOctReturnsEleven() {
        given().when().get("/api/dateparse/Sat/NOV").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/sat/DEC").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/dateparse/sat/Oct");
        act.then().body(equalTo("11"));
    }

    @Test(timeout = 60000)
    public void testParseNovReturnsTwelve() {
        given().when().get("/api/dateparse/thur/JAN").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/thur/FEB").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/dateparse/thur/Nov");
        act.then().body(equalTo("12"));
    }
}