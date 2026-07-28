package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class GammqTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE_URL");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGammqUsesGserWhenXLessThanAPlusOne() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/gammq/5.5/2.3");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqUsesGcfWhenXGreaterThanAPlusOne() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/gammq/5.5/1000.0");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqUsesGcfWhenXEqualsAPlusOne() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/gammq/2.0/3.0");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqInvalidANegativeProduces400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/gammq/-1.0/2.0");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqInvalidXNegativeProduces400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/gammq/5.0/-0.1");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqXZeroTriggersGserBranchAndReturns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/gammq/5.0/0.0");
        act.then().statusCode(200);
    }
}