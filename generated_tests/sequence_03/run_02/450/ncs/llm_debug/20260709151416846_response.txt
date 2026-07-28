package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;

public class GammqTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGammq_gserPath_status200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/gammq/5.5/0.001").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_gcfPath_status200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/gammq/5.5/1000.0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_zeroX_returns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        given().when().get("/api/gammq/2.0/0.0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_negativeA_returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/gammq/-1.0/2.3").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammq_negativeX_returns400() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        given().when().get("/api/gammq/5.5/-1.0").then().statusCode(400);
    }
}