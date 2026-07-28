package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.hamcrest.Matchers.lessThan;

public class GammqTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = System.getProperty("api.base", "http://localhost");
        RestAssured.port = Integer.parseInt(System.getProperty("api.port", "8080"));
        RestAssured.basePath = System.getProperty("api.basepath", "/");
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test(timeout = 60000)
    public void testGammqGserXZeroReturns200() {
        RestAssured.given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = RestAssured.given().when().get("/api/gammq/5.5/0");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqGcfLargeXReturns200() {
        RestAssured.given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = RestAssured.given().when().get("/api/gammq/5.5/1000");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqInvalidANonPositiveReturns400() {
        RestAssured.given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = RestAssured.given().when().get("/api/gammq/-1.0/2.0");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqInvalidXNegativeReturns400() {
        RestAssured.given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = RestAssured.given().when().get("/api/gammq/5.5/-0.1");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqGserConvergesReturns200() {
        RestAssured.given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = RestAssured.given().when().get("/api/gammq/5.5/0.001");
        act.then().statusCode(200);
    }
}