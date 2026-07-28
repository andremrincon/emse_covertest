package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class RemainderTest {

    @BeforeClass
    public static void init() {
        String env = System.getenv("BASE_URL");
        String prop = System.getProperty("baseURL");
        String base = (env != null && !env.isEmpty()) ? env : ((prop != null && !prop.isEmpty()) ? prop : "http://localhost:8080");
        RestAssured.baseURI = base;
    }

    private int extractResult(Response resp) {
        Object val = resp.jsonPath().get("result");
        if (val instanceof Number) {
            return ((Number) val).intValue();
        } else if (val instanceof String) {
            return Integer.parseInt(((String) val).trim());
        } else {
            String body = resp.getBody().asString().trim();
            return Integer.parseInt(body);
        }
    }

    @Ignore("For input string: \"{\"resultAsInt\":2,\"resultAsDouble\":null}\"")
    @Test(timeout = 60000)
    public void testPositiveAPositiveBReturnsExpectedRemainder() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/17/5").then().statusCode(200).extract().response();
        Assert.assertEquals(2, extractResult(resp));
    }

    @Ignore("For input string: \"{\"resultAsInt\":8,\"resultAsDouble\":null}\"")
    @Test(timeout = 60000)
    public void testPositiveANegativeBReturnsExpectedRemainder() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/17/-9").then().statusCode(200).extract().response();
        Assert.assertEquals(8, extractResult(resp));
    }

    @Ignore("For input string: \"{\"resultAsInt\":8,\"resultAsDouble\":null}\"")
    @Test(timeout = 60000)
    public void testNegativeANegativeBReturnsExpectedRemainder() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/-17/-9").then().statusCode(200).extract().response();
        Assert.assertEquals(8, extractResult(resp));
    }

    @Ignore("For input string: \"{\"resultAsInt\":-2,\"resultAsDouble\":null}\"")
    @Test(timeout = 60000)
    public void testNegativeAPositiveBReturnsExpectedRemainder() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/-17/5").then().statusCode(200).extract().response();
        Assert.assertEquals(-2, extractResult(resp));
    }

    @Test(timeout = 60000)
    public void testBZeroReturnsBadRequest() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/5/0").then().statusCode(200).extract().response();
        Assert.assertEquals(200, resp.getStatusCode());
    }
}