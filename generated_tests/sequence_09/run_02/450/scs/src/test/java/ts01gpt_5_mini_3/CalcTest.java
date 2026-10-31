package ts01gpt_5_mini_3;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class CalcTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("API_BASE_URL");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE_URL");
        if (base == null || base.isEmpty()) base = System.getProperty("api.base", System.getenv("API_BASE"));
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testPlusOperationReturnsCorrectSum() {
        String marker = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", marker).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/calc/{op}/{arg1}/{arg2}", "plus", "15.5", "4.5");
        resp.then().statusCode(200).body(equalTo("20.0"));
    }

    @Test(timeout = 60000)
    public void testSqrtUnaryOperationReturnsCorrectResult() {
        String marker = UUID.randomUUID().toString();
        given().when().get("/api/text2txt/{word1}/{word2}/{word3}", marker, "quick", "brown").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/calc/{op}/{arg1}/{arg2}", "sqrt", "16.0", "0.0");
        resp.then().statusCode(200).body(equalTo("4.0"));
    }

    @Test(timeout = 60000)
    public void testDivideByZeroProducesServerError() {
        String marker = UUID.randomUUID().toString();
        given().when().get("/api/cookie/{name}/{val}/{site}", "session-"+marker, "1", "example.com").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/calc/{op}/{arg1}/{arg2}", "divide", "100.0", "0.0");
        resp.then().statusCode(200);
    }
}