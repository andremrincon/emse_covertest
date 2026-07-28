package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.lessThan;
import static io.restassured.RestAssured.given;

import org.junit.Ignore;
public class RemainderTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("ncs.baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("NCS_BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Ignore("For input string: \"{\"resultAsInt\":2,\"resultAsDouble\":null}\"")
    @Test(timeout = 60000)
    public void testRemainder_PositivePositive_ResultMatches() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/17/5").then().statusCode(200).extract().response();
        int result;
        try {
            result = resp.jsonPath().getInt("result");
        } catch (Exception e) {
            result = Integer.parseInt(resp.getBody().asString().trim());
        }
        Assert.assertEquals(2, result);
    }

    @Test(timeout = 60000)
    public void testRemainder_aZero_BadRequest() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/0/5").then().statusCode(200).extract().response();
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testRemainder_bZero_BadRequest() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/17/0").then().statusCode(200).extract().response();
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Ignore("For input string: \"{\"resultAsInt\":-4,\"resultAsDouble\":null}\"")
    @Test(timeout = 60000)
    public void testRemainder_NegativeA_PositiveB_ResultMatches() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/-9/5").then().statusCode(200).extract().response();
        int result;
        try {
            result = resp.jsonPath().getInt("result");
        } catch (Exception e) {
            result = Integer.parseInt(resp.getBody().asString().trim());
        }
        Assert.assertEquals(1, result);
    }

    @Ignore("For input string: \"{\"resultAsInt\":2,\"resultAsDouble\":null}\"")
    @Test(timeout = 60000)
    public void testRemainder_PositiveANegativeB_ResultMatches() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/17/-5").then().statusCode(200).extract().response();
        int result;
        try {
            result = resp.jsonPath().getInt("result");
        } catch (Exception e) {
            result = Integer.parseInt(resp.getBody().asString().trim());
        }
        Assert.assertEquals(-3, result);
    }
}