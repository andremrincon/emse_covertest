package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ExpintTest {
    private static String base;

    @BeforeClass
    public static void setup() {
        base = System.getProperty("base.url", System.getenv("BASE_URL"));
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testExpintContinuedFraction() {
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/expint/3/2.5");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintSeriesNm1NonZero() {
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/expint/3/0.1");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintSeriesNm1Zero() {
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/expint/1/0.1");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintNZero() {
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/expint/0/2.5");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintXZeroForNGreaterThanOne() {
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/expint/2/0.0");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintInvalidXZeroWithNZero() {
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/expint/0/0.0");
        resp.then().statusCode(400);
    }
}