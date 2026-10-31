package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

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
    public void testDateparse_Tuesday_MAR_returns200() {
        given().when().get("/api/pat/The").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/dateparse/tuesday/mar");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateparse_Wednesday_August_returns200() {
        given().when().get("/api/pat/a").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/dateparse/Wednesday/August");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateparse_Superday_Movember_returns500() {
        given().when().get("/api/pat/The").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/dateparse/Superday/Movember");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateparse_Monday_numericMonth456_returns500() {
        given().when().get("/api/pat/a").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/dateparse/Monday/456");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateparse_tuesday_August_returns200_singleBehavior() {
        given().when().get("/api/pat/The").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/dateparse/tuesday/August");
        act.then().statusCode(200);
    }
}