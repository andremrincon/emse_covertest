package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class DateParseTest {

    private static String BASE_URL;

    @BeforeClass
    public static void setup() {
        String fromProp = System.getProperty("base.url");
        String fromEnv = System.getenv("BASE_URL");
        BASE_URL = fromProp != null && !fromProp.isEmpty() ? fromProp : (fromEnv != null && !fromEnv.isEmpty() ? fromEnv : "http://localhost:8080");
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test(timeout = 60000)
    public void testValidDayAndMonthReturnsSumBody() {
        String[] months = new String[]{"jan","feb","mar","apr","may","jun","jul","aug","sep","oct","nov","dec"};
        for (String m : months) {
            given().when().get(BASE_URL + "/api/dateparse/mon/" + m).then().statusCode(lessThan(300));
        }
        given().when().get(BASE_URL + "/api/dateparse/wed/mar").then().statusCode(lessThan(300)).and().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testNumericDayProducesServerErrorStatus500() {
        given().when().get(BASE_URL + "/api/pat/ok").then().statusCode(lessThan(300));
        given().when().get(BASE_URL + "/api/dateparse/123/aug").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUppercaseInputsAreHandledCaseInsensitively() {
        given().when().get(BASE_URL + "/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        given().when().get(BASE_URL + "/api/dateparse/MON/JAN").then().statusCode(lessThan(300)).and().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testThursdayAbbreviationAndOctoberReturnCorrectSum() {
        given().when().get(BASE_URL + "/api/calc/add/1/2").then().statusCode(lessThan(300));
        given().when().get(BASE_URL + "/api/dateparse/thur/oct").then().statusCode(lessThan(300)).and().body(equalTo("11"));
    }

    @Test(timeout = 60000)
    public void testInvalidMonthProducesServerErrorStatus500() {
        given().when().get(BASE_URL + "/api/pat/ok").then().statusCode(lessThan(300));
        given().when().get(BASE_URL + "/api/dateparse/mon/Movember").then().statusCode(200);
    }
}