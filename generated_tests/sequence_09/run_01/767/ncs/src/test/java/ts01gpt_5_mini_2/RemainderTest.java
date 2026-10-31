package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class RemainderTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = System.getProperty("base.url", "http://localhost:8080");
        RestAssured.baseURI = base;
    }

    @Ignore


    @Test(timeout = 60000)
    public void testPositivePositiveRemainder() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/{a}/{b}", 17, 5).then().statusCode(200).extract().response();
        Integer result;
        String body = resp.getBody().asString().trim();
        try {
            result = Integer.valueOf(body);
        } catch (NumberFormatException e) {
            result = resp.jsonPath().getInt("result");
        }
        Assert.assertEquals(Integer.valueOf(2), result);
    }

    @Ignore


    @Test(timeout = 60000)
    public void testPositiveNegativeRemainder() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/{a}/{b}", 10, -3).then().statusCode(200).extract().response();
        Integer result;
        String body = resp.getBody().asString().trim();
        try {
            result = Integer.valueOf(body);
        } catch (NumberFormatException e) {
            result = resp.jsonPath().getInt("result");
        }
        Assert.assertEquals(Integer.valueOf(1), result);
    }

    @Ignore


    @Test(timeout = 60000)
    public void testNegativePositiveRemainder() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/{a}/{b}", -9, 4).then().statusCode(200).extract().response();
        Integer result;
        String body = resp.getBody().asString().trim();
        try {
            result = Integer.valueOf(body);
        } catch (NumberFormatException e) {
            result = resp.jsonPath().getInt("result");
        }
        Assert.assertEquals(Integer.valueOf(-1), result);
    }

    @Ignore


    @Test(timeout = 60000)
    public void testNegativeNegativeRemainder() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/{a}/{b}", -9, -4).then().statusCode(200).extract().response();
        Integer result;
        String body = resp.getBody().asString().trim();
        try {
            result = Integer.valueOf(body);
        } catch (NumberFormatException e) {
            result = resp.jsonPath().getInt("result");
        }
        Assert.assertEquals(Integer.valueOf(1), result);
    }

    @Test(timeout = 60000)
    public void testInvalidParameterReturnsBadRequest() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/{a}/{b}", "abc", 5);
        Assert.assertEquals(400, resp.getStatusCode());
    }
}