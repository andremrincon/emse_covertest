package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FisherTest {

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
    public void testFisherOddOddReturns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/fisher/5/3/0.75");
        r.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherOddEvenReturns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/fisher/5/4/0.75");
        r.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherEvenOddWithZeroXReturns200() {
        given().when().get("/api/expint/3/2.5").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/fisher/4/3/0.0");
        r.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherEvenEvenReturns200() {
        given().when().get("/api/bessj/3/2.5").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/fisher/6/4/2.5");
        r.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherLargeXReturns200() {
        given().when().get("/api/gammq/5.5/2.3").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/fisher/11/5/1000000.0");
        r.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherSmallXReturns200() {
        given().when().get("/api/expint/1/0.1").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/fisher/7/7/1e-10");
        r.then().statusCode(200);
    }
}