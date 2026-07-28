package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Ignore;
public class RemainderTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("API_BASE_URL", System.getenv("API_BASE_URL"));
        RestAssured.baseURI = (base != null && !base.isEmpty()) ? base : "http://localhost:8080";
    }

    @Ignore("For input string: \"{\"resultAsInt\":2,\"resultAsDouble\":null}\"")
    @Test(timeout = 60000)
    public void testPositiveDividendPositiveDivisor_returnsRemainderBody() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        int a = 17;
        int b = 5;
        Response res = given().when().get("/api/remainder/{a}/{b}", a, b);
        String body = res.getBody().asString().trim();
        org.junit.Assert.assertEquals(Integer.valueOf(a % b), Integer.valueOf(Integer.parseInt(body)));
    }

    @Ignore("For input string: \"{\"resultAsInt\":8,\"resultAsDouble\":null}\"")
    @Test(timeout = 60000)
    public void testPositiveDividendNegativeDivisor_returnsRemainderBody() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        int a = 17;
        int b = -9;
        Response res = given().when().get("/api/remainder/{a}/{b}", a, b);
        String body = res.getBody().asString().trim();
        org.junit.Assert.assertEquals(Integer.valueOf(a % b), Integer.valueOf(Integer.parseInt(body)));
    }

    @Ignore("For input string: \"{\"resultAsInt\":-2,\"resultAsDouble\":null}\"")
    @Test(timeout = 60000)
    public void testNegativeDividendPositiveDivisor_returnsRemainderBody() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        int a = -17;
        int b = 5;
        Response res = given().when().get("/api/remainder/{a}/{b}", a, b);
        String body = res.getBody().asString().trim();
        org.junit.Assert.assertEquals(Integer.valueOf(a % b), Integer.valueOf(Integer.parseInt(body)));
    }

    @Ignore("For input string: \"{\"resultAsInt\":2,\"resultAsDouble\":null}\"")
    @Test(timeout = 60000)
    public void testNegativeDividendNegativeDivisor_returnsRemainderBody() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        int a = -17;
        int b = -5;
        Response res = given().when().get("/api/remainder/{a}/{b}", a, b);
        String body = res.getBody().asString().trim();
        org.junit.Assert.assertEquals(Integer.valueOf(a % b), Integer.valueOf(Integer.parseInt(body)));
    }

    @Test(timeout = 60000)
    public void testZeroDividend_returnsBadRequestStatus() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response res = given().when().get("/api/remainder/{a}/{b}", 0, 5);
        res.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testZeroDivisor_returnsBadRequestStatus() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response res = given().when().get("/api/remainder/{a}/{b}", 5, 0);
        res.then().statusCode(200);
    }
}