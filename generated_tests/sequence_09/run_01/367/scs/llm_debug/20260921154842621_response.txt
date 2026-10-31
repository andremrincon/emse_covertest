package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class DateParseTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("baseUrl");
        if (base == null) base = System.getenv("BASE_URL");
        if (base == null) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testMonJanReturns2() {
        given().when().get("/api/dateparse/wed/feb").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/wed/mar").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/wed/apr").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/wed/may").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/wed/jun").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/dateparse/mon/jan");
        resp.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testWedDecReturns13() {
        given().when().get("/api/dateparse/wed/jul").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/wed/aug").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/wed/sep").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/dateparse/wed/dec");
        resp.then().body(equalTo("13"));
    }

    @Test(timeout = 60000)
    public void testSunMarReturns4() {
        given().when().get("/api/dateparse/wed/oct").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/wed/nov").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/dateparse/sun/mar");
        resp.then().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testNonMatchingDayWithAugReturns8() {
        given().when().get("/api/dateparse/wed/dec").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/wed/jan").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/dateparse/xyz/aug");
        resp.then().body(equalTo("8"));
    }
}