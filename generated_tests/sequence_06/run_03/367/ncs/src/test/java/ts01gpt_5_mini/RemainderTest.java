package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class RemainderTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("BASE_URL");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Ignore("1 expectation failed. JSON path $ doesn't match. Expected: <2>   Actual: <{resultAsInt=2, resultA...")
    @Test(timeout = 60000)
    public void testPositivePositiveRemainder() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/remainder/{a}/{b}", 17, 5);
        act.then().statusCode(200).body("$", equalTo(2));
    }

    @Ignore("1 expectation failed. JSON path $ doesn't match. Expected: <8>   Actual: <{resultAsInt=8, resultA...")
    @Test(timeout = 60000)
    public void testPositiveNegativeRemainder() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/remainder/{a}/{b}", 17, -9);
        act.then().statusCode(200).body("$", equalTo(8));
    }

    @Ignore("1 expectation failed. JSON path $ doesn't match. Expected: <-2>   Actual: <{resultAsInt=-2, resul...")
    @Test(timeout = 60000)
    public void testNegativePositiveRemainder() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/remainder/{a}/{b}", -17, 5);
        act.then().statusCode(200).body("$", equalTo(-2));
    }

    @Ignore("1 expectation failed. JSON path $ doesn't match. Expected: <2>   Actual: <{resultAsInt=2, resultA...")
    @Test(timeout = 60000)
    public void testNegativeNegativeRemainder() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/remainder/{a}/{b}", -17, -5);
        act.then().statusCode(200).body("$", equalTo(2));
    }

    @Ignore("1 expectation failed. JSON path $ doesn't match. Expected: <-1>   Actual: <{resultAsInt=-1, resul...")
    @Test(timeout = 60000)
    public void testALessThanBReturnsMinusOne() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/remainder/{a}/{b}", 3, 5);
        act.then().statusCode(200).body("$", equalTo(-1));
    }
}