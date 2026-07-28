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
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testValidDayAndMonthReturnsCombinedValue() {
        given().when().get("/api/calc/add/1/2").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/dateparse/wed/mar");
        act.then().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testInvalidDayProducesServerError() {
        given().when().get("/api/pat/The%20quick%20brown%20fox%20jumps%20over%20the%20lazy%20dog.").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/dateparse/Superday/Movember");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCaseInsensitivityAndMonthAugust() {
        given().when().get("/api/costfuns/1/algorithm").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/dateparse/MON/Aug");
        act.then().body(equalTo("9"));
    }
}